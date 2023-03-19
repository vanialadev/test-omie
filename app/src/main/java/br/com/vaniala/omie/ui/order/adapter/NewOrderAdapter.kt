package br.com.vaniala.omie.ui.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.databinding.ItemOrderItemBinding
import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class NewOrderAdapter(
    var clickItem: (ItemModel) -> Unit = {},
) : RecyclerView.Adapter<NewOrderViewHolder>() {
    var items = emptyList<ItemModel>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                NewOrderListDiffCallback(
                    field,
                    value,
                ),
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderViewHolder {
        val binding =
            ItemOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewOrderViewHolder, position: Int) {
        holder.bind(items[position], clickItem)
    }

    override fun getItemCount() = items.size
}
