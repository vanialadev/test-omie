package br.com.vaniala.omie.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.vaniala.omie.database.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderEntity: OrderEntity)

    @Query("SELECT * FROM purchase_order WHERE id_user = :idUser")
    fun getAll(idUser: Long): Flow<List<OrderEntity>>
}
