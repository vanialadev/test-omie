package br.com.vaniala.omie.ui.order

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.ActivityNewOrderBinding
import br.com.vaniala.omie.databinding.BottomSheetLayoutBinding
import br.com.vaniala.omie.domain.model.ItemModel
import br.com.vaniala.omie.ui.order.adapter.BottomSheetAdapter
import br.com.vaniala.omie.ui.order.adapter.NewOrderAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import timber.log.Timber

class NewOrderActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewOrderBinding.inflate(layoutInflater)
    }

    private val adapter: NewOrderAdapter by lazy {
        NewOrderAdapter()
    }
    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = resources.getString(R.string.title_new_order)
        setRecyclerView()
        setFabButton()

        adapter.items = listOf(
            ItemModel(name = "Teclado Mecânico", price = 199.99),
            ItemModel(name = "Mouse Óptico", price = 29.99),
            ItemModel(name = "Monitor LED", price = 499.99),
            ItemModel(name = "Webcam Full HD", price = 79.99),
            ItemModel(name = "Fone de Ouvido Gamer", price = 149.99),
            ItemModel(name = "HD Externo", price = 119.99),
            ItemModel(name = "Placa de Vídeo", price = 799.99),
            ItemModel(name = "Cooler para Processador", price = 49.99),
            ItemModel(name = "Memória RAM", price = 149.99),
            ItemModel(name = "SSD", price = 249.99),
        )
    }

    private fun setRecyclerView() {
        binding.activityNewOrderRecyclerView.adapter = adapter
        adapter.clickItem = {
            Toast.makeText(this, "click item ${it.name}", Toast.LENGTH_SHORT).show()
            Timber.d("click")
        }
    }

    private fun setFabButton() {
        binding.activityNewOrderFab.setOnClickListener {
            Timber.d("click")
            showBottomSheet()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomSheetDialog?.dismiss()
    }

    private fun showBottomSheet() {
        val options = listOf(
            ItemModel(name = "Processador Intel Core i5", price = 399.99),
            ItemModel(name = "Placa-Mãe", price = 249.99),
            ItemModel(name = "Fonte de Alimentação", price = 149.99),
            ItemModel(name = "Cabo HDMI", price = 14.99),
            ItemModel(name = "Adaptador USB", price = 9.99),
            ItemModel(name = "Cadeira Gamer", price = 299.99),
            ItemModel(name = "Mesa para Computador", price = 199.99),
            ItemModel(name = "Hub USB", price = 19.99),
            ItemModel(name = "Leitor de Cartões de Memória", price = 29.99),
            ItemModel(name = "Estabilizador de Energia", price = 99.99),
        )
        val bottomSheetView = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog!!.setContentView(bottomSheetView.root)

        bottomSheetView.bottomSheetRecyclerView.adapter = BottomSheetAdapter(options)

        if (!isFinishing) {
            bottomSheetDialog!!.show()
        }
    }
}
