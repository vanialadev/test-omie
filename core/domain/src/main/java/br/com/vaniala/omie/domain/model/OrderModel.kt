package br.com.vaniala.omie.domain.model

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
data class OrderModel(
    val idOrder: Long = 0L,
    val totalPrice: Double,
    val idUser: Long,
    val name: String,
)
