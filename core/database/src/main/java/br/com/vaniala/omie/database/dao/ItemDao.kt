package br.com.vaniala.omie.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.vaniala.omie.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemsEntity: List<ItemEntity>)

    @Query("SELECT * FROM ITEM")
    fun getAll(): Flow<List<ItemEntity>>
}
