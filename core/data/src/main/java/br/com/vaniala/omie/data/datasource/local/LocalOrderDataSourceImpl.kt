package br.com.vaniala.omie.data.datasource.local

import br.com.vaniala.omie.database.dao.OrderDao
import br.com.vaniala.omie.database.entity.OrderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class LocalOrderDataSourceImpl @Inject constructor(
    private val orderDao: OrderDao,
) : LocalDataSource.Order {

    override fun getAllOrdersByUser(idUser: Long): Flow<List<OrderEntity>> =
        orderDao.getAll(idUser)
}
