package br.com.vaniala.omie.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
@Entity(
    tableName = "user",
)
@Parcelize
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_user")
    val idUser: Long = 0L,
    val name: String,
    val email: String,
    val password: String,
) : Parcelable
