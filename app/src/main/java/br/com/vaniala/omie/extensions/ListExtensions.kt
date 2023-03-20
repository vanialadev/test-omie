package br.com.vaniala.omie.extensions

import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
fun List<ItemModel>.isListValid(): Boolean = this.isNotEmpty()
