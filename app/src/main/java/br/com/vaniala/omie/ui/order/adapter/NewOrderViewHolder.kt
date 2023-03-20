package br.com.vaniala.omie.ui.order.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.ItemOrderItemBinding
import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class NewOrderViewHolder(
    private val binding: ItemOrderItemBinding,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: ItemModel,
        quantity: Int,
        priceTotal: Double,
        clickItem: (ItemModel) -> Unit,
        clickItemPlus: (ItemModel) -> Unit,
        clickItemMinus: (ItemModel) -> Unit,
    ) {
        binding.root.setOnClickListener {
            clickItem(item)
        }
        binding.itemOrderItemName.text = item.name

        binding.itemOrderItemQuantity.text =
            binding.root.context.getString(R.string.quantity, quantity.toString())

        binding.itemOrderItemTotalPrice.text =
            binding.root.context.getString(R.string.price_item, priceTotal.toString())

        binding.itemOrderItemPlus.setOnClickListener {
            clickItemPlus(item)
        }

        binding.itemOrderItemMinus.setOnClickListener {
            clickItemMinus(item)
        }
    }
}
