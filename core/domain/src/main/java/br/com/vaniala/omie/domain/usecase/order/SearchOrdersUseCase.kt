package br.com.vaniala.omie.domain.usecase.order

import br.com.vaniala.omie.domain.model.OrderModel
import br.com.vaniala.omie.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class SearchOrdersUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {
    operator fun invoke(idUser: Long): Flow<List<OrderModel>> =
        orderRepository.getAllOrders(idUser)
}
