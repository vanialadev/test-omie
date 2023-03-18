package br.com.vaniala.omie.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.vaniala.omie.data.model.UserModel
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
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
) : Parcelable

fun UserEntity.toModel() = UserModel(
    id = id,
    name = name,
    email = email,
    password = password,
)
