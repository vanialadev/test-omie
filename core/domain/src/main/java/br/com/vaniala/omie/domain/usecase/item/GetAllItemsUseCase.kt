package br.com.vaniala.omie.domain.usecase.item

import br.com.vaniala.omie.domain.model.ItemModel
import br.com.vaniala.omie.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */

class GetAllItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    operator fun invoke(): Flow<List<ItemModel>> =
        itemRepository.getAll()
}
