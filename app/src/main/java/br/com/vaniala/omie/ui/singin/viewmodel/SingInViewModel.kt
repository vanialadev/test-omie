package br.com.vaniala.omie.ui.singin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.omie.domain.usecase.login.AuthenticateUseCase
import br.com.vaniala.omie.extensions.isValidEmail
import br.com.vaniala.omie.extensions.isValidPassword
import br.com.vaniala.omie.utils.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@HiltViewModel
class SingInViewModel
@Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ValidationState())
    val state: Flow<ValidationState> = _state
    private val _error = MutableSharedFlow<Unit>()
    val error: Flow<Unit> = _error
    private val _navigation = MutableSharedFlow<Unit>()
    val navigation: Flow<Unit> = _navigation

    fun authenticate(email: String, password: String) {
        if (validateInputs(email, password)) {
            viewModelScope.launch {
                when (authenticateUseCase(email, password)) {
                    AuthenticateUseCase.Result.Failure -> _error.emit(Unit)

                    AuthenticateUseCase.Result.Success -> _navigation.emit(Unit)
                }
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isValidPassword()

        _state.value =
            _state.value.copy(isEmailValid = isEmailValid, isPasswordValid = isPasswordValid)

        return isEmailValid && isPasswordValid
    }
}
