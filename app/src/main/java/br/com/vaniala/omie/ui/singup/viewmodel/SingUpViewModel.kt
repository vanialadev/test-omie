package br.com.vaniala.omie.ui.singup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.omie.domain.model.UserModel
import br.com.vaniala.omie.domain.usecase.SingUpUseCase
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
 * on 18/03/23.
 *
 */
@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val singUpUseCase: SingUpUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ValidationState())
    val state: Flow<ValidationState> = _state
    private val _singUpSuccess = MutableSharedFlow<Unit>()
    val singUpSuccess: Flow<Unit> = _singUpSuccess
    private val _error = MutableSharedFlow<Unit>()
    val error: Flow<Unit> = _error

    fun singUp(userModel: UserModel) {
        if (validateInputs(userModel)) {
            viewModelScope.launch {
                when (singUpUseCase(userModel)) {
                    SingUpUseCase.Result.Failure -> _error.emit(Unit)
                    SingUpUseCase.Result.Success -> _singUpSuccess.emit(Unit)
                }
            }
        }
    }

    private fun validateInputs(userModel: UserModel): Boolean {
        val isEmailValid = userModel.email.isValidEmail()
        val isPasswordValid = userModel.password.isValidPassword()

        _state.value =
            _state.value.copy(isEmailValid = isEmailValid, isPasswordValid = isPasswordValid)

        return isEmailValid && isPasswordValid
    }
}
