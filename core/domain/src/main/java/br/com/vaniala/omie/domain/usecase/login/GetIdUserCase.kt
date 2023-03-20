package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class GetIdUserCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Long?> =
        userRepository.getId()
}
