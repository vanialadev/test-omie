package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
open class AddIdlDatastoreUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    open suspend operator fun invoke(id: Long) {
        userRepository.addIdDataStore(id)
    }
}
