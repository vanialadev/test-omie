package br.com.vaniala.omie.domain.model

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
data class ItemModel(
    val idItem: Long = 0L,
    val name: String,
    val price: Double,
    var quantity: Int = 1,
)
