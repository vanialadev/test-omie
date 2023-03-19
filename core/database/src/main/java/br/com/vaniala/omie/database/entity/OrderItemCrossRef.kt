package br.com.vaniala.omie.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@Entity(
    tableName = "order_item",
    primaryKeys = ["id_order", "id_item"],
    indices = [Index("id_item")],
)
class OrderItemCrossRef(
    @ColumnInfo(name = "id_order")
    val idOrder: Long,
    @ColumnInfo(name = "id_item")
    val idItem: Long,
    val quantity: Int,
)
