package br.com.vaniala.omie.data.mapperr

import br.com.vaniala.omie.database.entity.UserEntity
import br.com.vaniala.omie.domain.model.UserModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */

fun UserModel.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    password = password,
)
fun UserEntity.toModel() = UserModel(
    id = id,
    name = name,
    email = email,
    password = password,
)
