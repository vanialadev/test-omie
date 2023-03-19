package br.com.vaniala.omie.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.databinding.OrderItemBinding
import br.com.vaniala.omie.domain.model.OrderModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class HomeAdapter(
    var clickItem: (OrderModel) -> Unit = {},
) : RecyclerView.Adapter<HomeViewHolder>() {
    var orders = emptyList<OrderModel>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                HomeListDiffCallback(
                    field,
                    value,
                ),
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(orders[position], clickItem)
    }

    override fun getItemCount() = orders.size
}
