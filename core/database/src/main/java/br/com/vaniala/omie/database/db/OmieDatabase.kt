package br.com.vaniala.omie.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.vaniala.omie.database.dao.ItemDao
import br.com.vaniala.omie.database.dao.OrderDao
import br.com.vaniala.omie.database.dao.OrderWithItemDao
import br.com.vaniala.omie.database.dao.UserDao
import br.com.vaniala.omie.database.entity.ItemEntity
import br.com.vaniala.omie.database.entity.OrderEntity
import br.com.vaniala.omie.database.entity.OrderItemCrossRef
import br.com.vaniala.omie.database.entity.UserEntity

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
@Database(
    version = 1,
    entities = [
        UserEntity::class,
        OrderEntity::class,
        ItemEntity::class,
        OrderItemCrossRef::class,
    ],
    exportSchema = true,
)
abstract class OmieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao
    abstract fun itemDao(): ItemDao
    abstract fun orderWithItemDao(): OrderWithItemDao
}
