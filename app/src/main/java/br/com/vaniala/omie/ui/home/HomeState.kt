package br.com.vaniala.omie.ui.home

import br.com.vaniala.omie.domain.model.OrderModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
sealed class HomeState {
    object Loading : HomeState()
    data class EmptyList(val successMessage: String) : HomeState()
    data class Success(val orders: List<OrderModel>) : HomeState()
    data class Error(val message: String) : HomeState()
}
