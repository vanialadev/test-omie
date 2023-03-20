package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
class IsLoggedUserCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Boolean> =
        userRepository.isLogged()
}
