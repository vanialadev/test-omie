package br.com.vaniala.omie.ui.order.adapter.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.databinding.NewItemOrderItemBinding
import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class BottomSheetAdapter(
    var clickItem: (ItemModel) -> Unit = {},
) :
    RecyclerView.Adapter<BottomSheetViewHolder>() {
    var items = emptyList<ItemModel>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                BottomSheetDiffCallback(
                    field,
                    value,
                ),
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        val binding =
            NewItemOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.bind(items[position], clickItem)
    }

    override fun getItemCount() = items.size
}
