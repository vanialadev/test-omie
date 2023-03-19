package br.com.vaniala.omie.data.mapper

import br.com.vaniala.omie.database.entity.ItemEntity
import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
fun ItemModel.toEntity() = ItemEntity(
    idItem = 0L,
    name = name,
    price = price,
)

fun ItemEntity.toModel() = ItemModel(
    name = name,
    price = price,
)
