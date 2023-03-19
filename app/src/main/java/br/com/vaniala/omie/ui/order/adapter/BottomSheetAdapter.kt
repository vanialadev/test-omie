package br.com.vaniala.omie.ui.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.NewItemOrderItemBinding
import br.com.vaniala.omie.domain.model.ItemModel

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class BottomSheetAdapter(
    private val items: List<ItemModel>,
) :
    RecyclerView.Adapter<BottomSheetAdapter.BottomSheetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        val binding =
            NewItemOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class BottomSheetViewHolder(private val binding: NewItemOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemModel) {
            binding.itemItemName.text = item.name
            binding.itemItemPrice.text =
                binding.root.context.getString(R.string.price_item, item.price.toString())

            binding.itemItemButton.setOnClickListener {
                Toast.makeText(binding.root.context, "click item ${item.name}", Toast.LENGTH_SHORT)
                    .show()
//        }
            }
        }
    }
}
