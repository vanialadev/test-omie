package br.com.vaniala.omie.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
data class OrderWithItem(
    @Embedded val order: OrderEntity,
    @Relation(
        parentColumn = "id_order",
        entityColumn = "id_item",
        associateBy = Junction(OrderItemCrossRef::class),
    )
    val items: List<ItemEntity>,
)
