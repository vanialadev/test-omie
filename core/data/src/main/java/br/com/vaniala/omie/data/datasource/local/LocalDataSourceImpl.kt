package br.com.vaniala.omie.data.datasource.local

import br.com.vaniala.omie.database.dao.ItemDao
import br.com.vaniala.omie.database.dao.UserDao
import br.com.vaniala.omie.database.entity.ItemEntity
import br.com.vaniala.omie.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val itemDao: ItemDao,
) : LocalDataSource {
    override suspend fun insert(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    override fun searchByEmail(email: String): Flow<UserEntity?> =
        userDao.searchByEmail(email)

    override fun authenticate(email: String, password: String): Flow<UserEntity?> =
        userDao.authenticate(email, password)

    override fun checkEmailExist(email: String): Flow<Boolean> =
        userDao.checkEmailExist(email)

    override suspend fun insertAllItems(itemsEntity: List<ItemEntity>) {
        itemDao.insertAll(itemsEntity)
    }

    override fun getAllItems(): Flow<List<ItemEntity>> =
        itemDao.getAll()
}
