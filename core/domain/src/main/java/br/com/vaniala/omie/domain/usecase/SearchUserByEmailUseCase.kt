package br.com.vaniala.omie.domain.usecase

import br.com.vaniala.omie.domain.model.UserModel
import br.com.vaniala.omie.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
class SearchUserByEmailUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(email: String): Flow<UserModel?> {
        return userRepository.searchByEmail(email)
    }
}