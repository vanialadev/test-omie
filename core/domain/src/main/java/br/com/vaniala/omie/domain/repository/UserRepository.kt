package br.com.vaniala.omie.domain.repository

import br.com.vaniala.omie.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
interface UserRepository {
    suspend fun authenticate(email: String, password: String)

    suspend fun searchByEmail(email: String): Flow<UserModel?>

    suspend fun addEmailDataStore(email: String)

    suspend fun isLogged(): Flow<Boolean>
}
