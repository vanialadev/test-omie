package br.com.vaniala.omie.domain.usecase.order

import br.com.vaniala.omie.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 20/03/23.
 *
 */
class GetTotalPriceUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {
    operator fun invoke(idUser: Long): Flow<Double> =
        orderRepository.getTotal(idUser)
}
