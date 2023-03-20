package br.com.vaniala.omie.data.datasource.local

import br.com.vaniala.omie.database.dao.OrderDao
import br.com.vaniala.omie.database.dao.OrderWithItemDao
import br.com.vaniala.omie.database.entity.ItemEntity
import br.com.vaniala.omie.database.entity.OrderEntity
import br.com.vaniala.omie.database.entity.OrderItemCrossRef
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class LocalOrderDataSourceImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val orderWithItemDao: OrderWithItemDao,
) : LocalDataSource.Order {

    override fun getAllOrdersByUser(idUser: Long): Flow<List<OrderEntity>> =
        orderDao.getAll(idUser)

    override suspend fun insertOrderWithItems(orderEntity: OrderEntity, items: List<ItemEntity>) {
        val orderId: Long = orderDao.insert(orderEntity)
        val orderItemCrossRef: List<OrderItemCrossRef> = items.map { itemEntity ->
            OrderItemCrossRef(orderId, itemEntity.idItem, itemEntity.quantity)
        }
        orderWithItemDao.insertOrderItemCrossRef(orderItemCrossRef)
    }

    override fun getTotal(idUser: Long): Flow<Double> {
        return orderDao.getTotalPrice(idUser)
    }
}
