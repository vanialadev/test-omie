package br.com.vaniala.omie.database.entity

import androidx.room.*

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@Entity(
    tableName = "purchase_order",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id_user"],
            childColumns = ["id_user"],
        ),
    ],
    indices = [Index("id_user")],
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_order")
    val idOrder: Long = 0L,
    val totalPrice: Double,
    @ColumnInfo(name = "id_user")
    val idUser: Long,
    val name: String,
)
