package br.com.vaniala.omie.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.vaniala.omie.domain.model.OrderModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class HomeListDiffCallback constructor(
    private val oldList: List<OrderModel>,
    private val newList: List<OrderModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].idOrder == newList[newItemPosition].idOrder

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        true
}
