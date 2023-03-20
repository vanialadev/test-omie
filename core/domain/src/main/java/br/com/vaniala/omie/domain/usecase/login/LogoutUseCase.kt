package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() {
        userRepository.removeFromDatastore()
    }
}
