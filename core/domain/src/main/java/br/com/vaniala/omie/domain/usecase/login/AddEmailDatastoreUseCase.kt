package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
open class AddEmailDatastoreUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    open suspend operator fun invoke(email: String) {
        userRepository.addEmailDataStore(email)
    }
}
