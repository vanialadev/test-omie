package br.com.vaniala.omie.domain.usecase.login

import kotlinx.coroutines.flow.firstOrNull
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
open class AuthenticateUseCase @Inject constructor(
    private val searchUserByEmailUseCase: SearchUserByEmailUseCase,
    private val addEmailDatastoreUseCase: AddEmailDatastoreUseCase,
    private val addIdlDatastoreUseCase: AddIdlDatastoreUseCase,
) {
    open suspend operator fun invoke(email: String, password: String): Result {
        try {
            if (checkPasswordMatchers(email, password)) return Result.Failure
            addEmailDatastoreUseCase(email)
            Timber.d("Success! ")
            return Result.Success
        } catch (e: Throwable) {
            Timber.e("AuthenticateUseCase: failed, exception: ${e.message}")
            return Result.Failure
        }
    }

    private suspend fun checkPasswordMatchers(
        email: String,
        password: String,
    ): Boolean {
        val userModel = searchUserByEmailUseCase(email).firstOrNull()
        if (userModel?.password != password) {
            Timber.e("AuthenticateUseCase: failed, passwords do not match")
            return true
        }
        addIdlDatastoreUseCase(userModel.idUser)
        return false
    }

    sealed class Result {
        object Success : Result()
        object Failure : Result()
    }
}
