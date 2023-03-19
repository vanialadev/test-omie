package br.com.vaniala.omie.domain.repository

import br.com.vaniala.omie.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
interface OrderRepository {

    fun getAllOrdersByUser(idUser: Long): Flow<List<OrderModel>>
}
