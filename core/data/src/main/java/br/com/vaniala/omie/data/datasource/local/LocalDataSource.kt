package br.com.vaniala.omie.data.datasource.local

import br.com.vaniala.omie.database.entity.ItemEntity
import br.com.vaniala.omie.database.entity.OrderEntity
import br.com.vaniala.omie.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
interface LocalDataSource {

    interface Login {
        suspend fun insert(userEntity: UserEntity): Long

        fun searchByEmail(email: String): Flow<UserEntity?>

        fun authenticate(email: String, password: String): Flow<UserEntity?>
        fun checkEmailExist(email: String): Flow<Boolean>
    }

    interface Item {

        suspend fun insertAllItems(itemsEntity: List<ItemEntity>)

        fun getAllItems(): Flow<List<ItemEntity>>
    }

    interface Order {

        fun getAllOrdersByUser(idUser: Long): Flow<List<OrderEntity>>
    }
}
