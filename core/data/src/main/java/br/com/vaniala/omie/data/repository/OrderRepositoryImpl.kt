package br.com.vaniala.omie.data.repository

import br.com.vaniala.omie.data.datasource.local.LocalDataSource
import br.com.vaniala.omie.data.mapper.toModel
import br.com.vaniala.omie.domain.repository.OrderRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class OrderRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.Order,
) : OrderRepository {

    override fun getAllOrdersByUser(idUser: Long) =
        localDataSource.getAllOrdersByUser(idUser).map { ordersEntity ->
            ordersEntity.map { it.toModel() }
        }
}
