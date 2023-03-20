package br.com.vaniala.omie.ui.order.adapter.bottomsheet

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.NewItemOrderItemBinding
import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class BottomSheetViewHolder(private val binding: NewItemOrderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ItemModel, clickItem: (ItemModel) -> Unit) {
        binding.itemItemName.text = item.name
        binding.itemItemPrice.text =
            binding.root.context.getString(R.string.price_item, item.price.toString())

        binding.itemItemButton.setOnClickListener {
            clickItem(item)
        }
    }
}
