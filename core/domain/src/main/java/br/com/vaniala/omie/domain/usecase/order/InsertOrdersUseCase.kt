package br.com.vaniala.omie.domain.usecase.order

import br.com.vaniala.omie.domain.model.ItemModel
import br.com.vaniala.omie.domain.model.OrderModel
import br.com.vaniala.omie.domain.repository.OrderRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class InsertOrdersUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {
    suspend operator fun invoke(orderModel: OrderModel, itemsModel: List<ItemModel>): Result {
        return try {
            orderRepository.insertOrderWithItems(orderModel, itemsModel)
            Timber.d("Success")
            Result.Success
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.d("Failure")
            Result.Failure
        }
    }

    sealed class Result {
        object Success : Result()
        object Failure : Result()
    }
}
