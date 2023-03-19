package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.model.UserModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 18/03/23.
 *
 */
class SingUpUseCase @Inject constructor(
    private val checkEmailUseCase: CheckEmailUseCase,
    private val insertUserToDbUseCase: InsertUserToDbUseCase,
    private val addIdlDatastoreUseCase: AddIdlDatastoreUseCase,

) {
    suspend operator fun invoke(userModel: UserModel): Result {
        Timber.d("invoke ${userModel.email}")
        val userExists = checkEmailUseCase(userModel.email)
        return if (!userExists) {
            val idUser = insertUserToDbUseCase(userModel)
            addIdlDatastoreUseCase(idUser)
            Timber.d("Success")
            Result.Success
        } else {
            Timber.d("Failure")
            Result.Failure
        }
    }

    sealed class Result {
        object Success : Result()
        object Failure : Result()
    }
}
