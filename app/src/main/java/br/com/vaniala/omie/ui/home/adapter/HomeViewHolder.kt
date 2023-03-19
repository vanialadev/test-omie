package br.com.vaniala.omie.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.OrderItemBinding
import br.com.vaniala.omie.domain.model.OrderModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class HomeViewHolder(
    private val binding: OrderItemBinding,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(order: OrderModel, clickItem: (OrderModel) -> Unit) {
        binding.root.setOnClickListener {
            clickItem(order)
        }
        binding.orderItemName.text =
            binding.root.context.getString(R.string.order_name, order.idOrder.toString())
    }
}
