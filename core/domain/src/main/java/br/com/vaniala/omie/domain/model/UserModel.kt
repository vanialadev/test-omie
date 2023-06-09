package br.com.vaniala.omie.domain.model

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */

data class UserModel(
    val idUser: Long = 0L,
    val name: String,
    val email: String,
    val password: String,
)
