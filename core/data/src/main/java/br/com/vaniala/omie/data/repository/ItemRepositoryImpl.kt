package br.com.vaniala.omie.data.repository

import br.com.vaniala.omie.data.datasource.local.LocalDataSource
import br.com.vaniala.omie.data.mapper.toEntity
import br.com.vaniala.omie.data.mapper.toModel
import br.com.vaniala.omie.domain.model.ItemModel
import br.com.vaniala.omie.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class ItemRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.Item,
) : ItemRepository {
    override suspend fun insertAll(itemsModel: List<ItemModel>) {
        val itemsEntity = itemsModel.map { it.toEntity() }
        localDataSource.insertAllItems(itemsEntity)
    }

    override fun getAll(): Flow<List<ItemModel>> =
        localDataSource.getAllItems().map { itemsEntity ->
            itemsEntity.map { it.toModel() }
        }
}
