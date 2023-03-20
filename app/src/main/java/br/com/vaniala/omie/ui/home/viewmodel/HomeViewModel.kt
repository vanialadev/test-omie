package br.com.vaniala.omie.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.omie.domain.usecase.login.GetIdUserCase
import br.com.vaniala.omie.domain.usecase.login.IsLoggedUserCase
import br.com.vaniala.omie.domain.usecase.login.LogoutUseCase
import br.com.vaniala.omie.domain.usecase.order.GetAllOrdersByUserUseCase
import br.com.vaniala.omie.domain.usecase.order.GetTotalPriceUseCase
import br.com.vaniala.omie.ui.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    private val getIdUserCase: GetIdUserCase,
    private val getAllOrdersByUserUseCase: GetAllOrdersByUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getTotalPriceUseCase: GetTotalPriceUseCase,

) : ViewModel() {
    private val _logout = MutableSharedFlow<Unit>()
    val logout: Flow<Unit> = _logout

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _uiState = MutableSharedFlow<HomeState>()
    val uiState: Flow<HomeState> = _uiState.distinctUntilChanged()

    private val _price = MutableStateFlow(0.0)
    val price = _price.asStateFlow()

    private fun getPrice(idUser: Long) {
        viewModelScope.launch {
            getTotalPriceUseCase(idUser).collect {
                _price.emit(it)
            }
        }
    }

    private fun getOrders(idUser: Long) {
        viewModelScope.launch {
            _uiState.emit(HomeState.Loading)
            try {
                getAllOrdersByUserUseCase(idUser).onEach { orders ->
                    if (orders.isEmpty()) {
                        _uiState.emit(HomeState.EmptyList("Nenhum pedido encontrado"))
                    } else {
                        _uiState.emit(HomeState.Success(orders))
                    }
                }.launchIn(this)
            } catch (e: Throwable) {
                Timber.e("HomeViewModel: failed, exception: ${e.message}")
                _uiState.emit(HomeState.Error("Erro ao buscar pedidos"))
            }
        }
    }

    fun logOutClicked() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("HomeViewModel: onCleared")
    }

    init {
        viewModelScope.launch {
            Timber.d("HomeViewModel: init viewmode")
            isLoggedUserCase().onEach { isLogged ->
                Timber.d(isLogged.toString())
                if (isLogged) {
                    _isLoading.value = false
                } else {
                    _logout.emit(Unit)
                }
            }.launchIn(this)

            getIdUserCase().onEach { id ->
                id?.let {
                    getPrice(it)
                    getOrders(it)
                }
            }.launchIn(this)
        }
    }
}
