package br.com.vaniala.omie.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_item")
    val idItem: Long = 0L,
    val name: String,
    val price: Double,
)
