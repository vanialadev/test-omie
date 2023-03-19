package br.com.vaniala.omie.data.datasource.local

import br.com.vaniala.omie.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
interface LocalDataSource {
    suspend fun insert(userEntity: UserEntity)

    fun searchByEmail(email: String): Flow<UserEntity?>

    fun authenticate(email: String, password: String): Flow<UserEntity?>
    fun checkEmailExist(email: String): Flow<Boolean>
}
