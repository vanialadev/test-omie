package br.com.vaniala.omie.domain.usecase

import br.com.vaniala.omie.domain.model.UserModel
import br.com.vaniala.omie.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 18/03/23.
 *
 */
class InsertUserToDbUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userModel: UserModel) {
        userRepository.insertUser(userModel)
    }
}
