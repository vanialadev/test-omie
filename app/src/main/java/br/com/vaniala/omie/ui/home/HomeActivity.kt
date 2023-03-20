package br.com.vaniala.omie.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.ActivityHomeBinding
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.ui.home.adapter.HomeAdapter
import br.com.vaniala.omie.ui.home.viewmodel.HomeViewModel
import br.com.vaniala.omie.ui.order.NewOrderActivity
import br.com.vaniala.omie.ui.singin.SingInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val adapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContentView(binding.root)
        title = getString(R.string.title_home)
        setRecyclerView()
        setFabButton()

        lifecycleScope.launch {
            viewModel.price.onEach {
                binding.activityHomeTotalOrdersText.text =
                    getString(R.string.total_orders, it.toString())
            }.launchIn(this)
        }

        lifecycleScope.launch {
            viewModel.logout.onEach {
                goTo(SingInActivity::class.java)
            }.launchIn(this)
        }

        lifecycleScope.launch {
            viewModel.uiState
                .onEach {
                    when (it) {
                        is HomeState.Loading -> {
                            Timber.d("Loading")
                            binding.activityHomeInfoText.visibility = View.VISIBLE
                            binding.activityHomeRecyclerView.visibility = View.GONE
                            binding.activityHomeInfoText.text = getString(R.string.loading)
                        }
                        is HomeState.Success -> {
                            adapter.orders = it.orders
                            Timber.d("Success")
                            binding.activityHomeInfoText.visibility = View.GONE
                            binding.activityHomeRecyclerView.visibility = View.VISIBLE
                        }
                        is HomeState.EmptyList -> {
                            Timber.d("EmptyList")
                            binding.activityHomeInfoText.visibility = View.VISIBLE
                            binding.activityHomeRecyclerView.visibility = View.GONE
                            binding.activityHomeInfoText.text = getString(R.string.empty_list)
                        }
                        is HomeState.Error -> {
                            Toast.makeText(
                                this@HomeActivity,
                                it.message,
                                Toast.LENGTH_SHORT,
                            )
                                .show()
                            Timber.d("Error")
                        }
                    }
                }
                .launchIn(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                viewModel.logOutClicked()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecyclerView() {
        binding.activityHomeRecyclerView.adapter = adapter
        adapter.clickItem = {
            Toast.makeText(this, "Valor R$:${it.totalPrice}", Toast.LENGTH_SHORT).show()
            Timber.d("click")
        }
    }

    private fun setFabButton() {
        binding.activityHomeExtendFab.setOnClickListener {
            Timber.d("click")
//            finish()
            goTo(NewOrderActivity::class.java)
        }
    }
}
