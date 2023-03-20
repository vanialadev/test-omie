package br.com.vaniala.omie.data.repository

import br.com.vaniala.omie.data.datasource.local.LocalDataSource
import br.com.vaniala.omie.data.mapper.toEntity
import br.com.vaniala.omie.data.mapper.toModel
import br.com.vaniala.omie.domain.model.ItemModel
import br.com.vaniala.omie.domain.model.OrderModel
import br.com.vaniala.omie.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
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

    override suspend fun insertOrderWithItems(orderModel: OrderModel, itemsModel: List<ItemModel>) {
        val orderEntity = orderModel.toEntity()
        val itemEntity = itemsModel.map { it.toEntity() }
        localDataSource.insertOrderWithItems(orderEntity, itemEntity)
    }

    override fun getTotal(idUser: Long): Flow<Double> {
        return localDataSource.getTotal(idUser)
    }
}
