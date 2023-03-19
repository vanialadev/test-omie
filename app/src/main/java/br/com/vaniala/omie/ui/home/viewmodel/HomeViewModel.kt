package br.com.vaniala.omie.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.omie.domain.usecase.IsLoggedUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val isLoggedUserCase: IsLoggedUserCase,
) : ViewModel() {
    private val _logout = MutableSharedFlow<Unit>()
    val logout: Flow<Unit> = _logout

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            isLoggedUserCase().onEach { isLogged ->
                Timber.d(isLogged.toString())
                if (isLogged) {
                    _isLoading.value = false
                } else {
                    _logout.emit(Unit)
                }
            }.launchIn(this)
        }
    }
}
