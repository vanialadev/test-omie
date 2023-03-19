package br.com.vaniala.omie.database.dao

import androidx.room.*
import br.com.vaniala.omie.database.entity.OrderItemCrossRef
import br.com.vaniala.omie.database.entity.OrderWithItem

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@Dao
interface OrderWithItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderWithItem: OrderItemCrossRef)

    @Transaction
    @Query("SELECT * FROM purchase_order ")
    fun getOrdersWithItems(): List<OrderWithItem>
}
