package br.com.vaniala.omie.data.mapper

import br.com.vaniala.omie.database.entity.OrderEntity
import br.com.vaniala.omie.domain.model.OrderModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
fun OrderModel.toEntity() = OrderEntity(
    idOrder = 0L,
    idUser = idUser,
    totalPrice = totalPrice,
)

fun OrderEntity.toModel() = OrderModel(
    idOrder = idOrder,
    idUser = idUser,
    totalPrice = totalPrice,
)
