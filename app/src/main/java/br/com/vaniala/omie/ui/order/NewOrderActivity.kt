package br.com.vaniala.omie.ui.order

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.ActivityNewOrderBinding
import br.com.vaniala.omie.databinding.BottomSheetLayoutBinding
import br.com.vaniala.omie.ui.order.adapter.NewOrderAdapter
import br.com.vaniala.omie.ui.order.adapter.bottomsheet.BottomSheetAdapter
import br.com.vaniala.omie.ui.order.viewmodel.NewOrdersViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NewOrderActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewOrderBinding.inflate(layoutInflater)
    }

    private val adapter: NewOrderAdapter by lazy {
        NewOrderAdapter()
    }

    private val viewModel: NewOrdersViewModel by viewModels()

    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = resources.getString(R.string.title_new_order)
        setRecyclerView()
        setFabButton()
        setButton()

        lifecycleScope.launch {
            viewModel.orderItemsState.collect {
                adapter.items = it.items
                binding.activityNewOrderTotalItems.text = it.totalItems.toString()
                binding.activityNewOrderTotalPrice.text = it.priceTotal.toString()
            }
        }
        lifecycleScope.launch {
            viewModel.error.collect {
                Toast.makeText(this@NewOrderActivity, it, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        lifecycleScope.launch {
            viewModel.itemAdapter.collect {
                adapter.quantity = it.quantity
                adapter.priceTotal = it.priceTotal
            }
        }

        lifecycleScope.launch {
            viewModel.state.onEach { state ->
                binding.activityNewOrderTextInputLayoutName.error = if (state.isNameValid) {
                    null
                } else {
                    resources.getString(R.string.name_required)
                }
                if (!state.isListValid) {
                    Toast
                        .makeText(
                            this@NewOrderActivity,
                            getString(R.string.add_least_a_item),
                            Toast.LENGTH_SHORT,
                        ).show()
                }
                Timber.d(state.toString())
            }.launchIn(this)
        }

        lifecycleScope.launch {
            viewModel.saveOrders.onEach {
                finishActivity()
            }.launchIn(this)
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        goTo(HomeActivity::class.java)
//    }

    private fun setRecyclerView() {
        binding.activityNewOrderRecyclerView.adapter = adapter
        adapter.clickItem = {
//            Toast.makeText(this, "click item ${it.name}", Toast.LENGTH_SHORT).show()
            Timber.d("click")
        }

        adapter.clickItemPlus = {
            lifecycleScope.launch {
                viewModel.updateItemQuantity(it, 1)
                adapter.notifyItemChanged(adapter.items.indexOf(it))
            }
        }
        adapter.clickItemMinus = {
            lifecycleScope.launch {
                viewModel.updateItemQuantity(it, -1)
                adapter.notifyItemChanged(adapter.items.indexOf(it))
            }
        }
    }

    private fun setFabButton() {
        binding.activityNewOrderFab.setOnClickListener {
            Timber.d("click")
            showBottomSheet()
        }
    }

    private fun setButton() {
        binding.activityNewOrderButtonSaveOrder.setOnClickListener {
            val name = binding.activityNewOrderNameClient.text.toString()
            viewModel.saveOrder(name)
        }
    }

    private fun finishActivity() {
//        goTo(HomeActivity::class.java)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomSheetDialog?.dismiss()
    }

    private fun showBottomSheet() {
        val bottomSheetView = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog!!.setContentView(bottomSheetView.root)

        bottomSheetView.bottomSheetRecyclerView.adapter = BottomSheetAdapter()

        val bottomSheetAdapter =
            bottomSheetView.bottomSheetRecyclerView.adapter as BottomSheetAdapter

        lifecycleScope.launch {
            viewModel.items.collect { items ->
                bottomSheetAdapter.items = items
            }
        }

        bottomSheetAdapter.clickItem = {
            Timber.d("click")
            bottomSheetDialog?.dismiss()
            lifecycleScope.launch {
                viewModel.addItemToOrder(it)
            }
        }

        if (!isFinishing) {
            bottomSheetDialog!!.show()
        }
    }
}
