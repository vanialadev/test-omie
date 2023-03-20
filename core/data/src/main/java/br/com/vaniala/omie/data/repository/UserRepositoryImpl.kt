package br.com.vaniala.omie.data.repository

import br.com.vaniala.omie.data.datasource.local.LocalDataSource
import br.com.vaniala.omie.data.mapper.toEntity
import br.com.vaniala.omie.data.mapper.toModel
import br.com.vaniala.omie.data.utils.DATASTORE_LOGGED_EMAIL_KEY
import br.com.vaniala.omie.data.utils.DATASTORE_LOGGED_ID_KEY
import br.com.vaniala.omie.data.utils.DatastoreManager
import br.com.vaniala.omie.domain.model.UserModel
import br.com.vaniala.omie.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource.Login,
    private val datastore: DatastoreManager,
) : UserRepository {

    override suspend fun authenticate(email: String, password: String) {
        localDataSource.authenticate(email, password)
    }

    override fun searchByEmail(email: String): Flow<UserModel?> {
        return localDataSource.searchByEmail(email).map { it?.toModel() }
    }

    override suspend fun addEmailDataStore(email: String) {
        datastore.addToDatastore(DATASTORE_LOGGED_EMAIL_KEY, email)
    }

    override suspend fun addIdDataStore(id: Long) {
        datastore.addToDatastore(DATASTORE_LOGGED_ID_KEY, id.toString())
    }

    override fun isLogged(): Flow<Boolean> {
        return datastore.observeKeyValue(DATASTORE_LOGGED_EMAIL_KEY).map {
            it != null
        }
    }

    override fun getId(): Flow<Long?> {
        return datastore.observeKeyValue(DATASTORE_LOGGED_ID_KEY).map {
            it?.toLong()
        }
    }

    override suspend fun insertUser(userModel: UserModel): Long {
        val userEntity = userModel.toEntity()
        return localDataSource.insert(userEntity)
    }

    override fun checkEmailExist(email: String): Flow<Boolean> =
        localDataSource.checkEmailExist(email)

    override suspend fun removeFromDatastore() {
        datastore.removeFromDatastore(DATASTORE_LOGGED_EMAIL_KEY)
        datastore.removeFromDatastore(DATASTORE_LOGGED_ID_KEY)
    }
}
