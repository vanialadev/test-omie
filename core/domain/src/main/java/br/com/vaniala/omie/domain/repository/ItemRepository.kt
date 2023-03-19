package br.com.vaniala.omie.domain.repository

import br.com.vaniala.omie.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
interface ItemRepository {

    suspend fun insertAll(itemsModel: List<ItemModel>)

    fun getAll(): Flow<List<ItemModel>>
}
