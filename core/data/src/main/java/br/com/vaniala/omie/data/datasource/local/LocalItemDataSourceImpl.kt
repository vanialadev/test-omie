package br.com.vaniala.omie.data.datasource.local

import br.com.vaniala.omie.database.dao.ItemDao
import br.com.vaniala.omie.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class LocalItemDataSourceImpl @Inject constructor(
    private val itemDao: ItemDao,
) : LocalDataSource.Item {
    override suspend fun insertAllItems(itemsEntity: List<ItemEntity>) {
        itemDao.insertAll(itemsEntity)
    }

    override fun getAllItems(): Flow<List<ItemEntity>> =
        itemDao.getAll()
}
