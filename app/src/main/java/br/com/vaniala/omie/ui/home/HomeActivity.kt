package br.com.vaniala.omie.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.databinding.ActivityHomeBinding
import br.com.vaniala.omie.domain.model.OrderModel
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.ui.home.adapter.HomeAdapter
import br.com.vaniala.omie.ui.home.viewmodel.HomeViewModel
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

        setRecyclerView()
        setFabButton()

        lifecycleScope.launch {
            viewModel.logout.onEach {
                goTo(SingInActivity::class.java)
            }.launchIn(this)
        }

        adapter.orders = listOf(
            OrderModel(1, 10.0, 1),
            OrderModel(2, 10.0, 1),
            OrderModel(3, 10.0, 1),
            OrderModel(4, 10.0, 1),
            OrderModel(5, 10.0, 1),
        )
    }

    private fun setRecyclerView() {
        binding.activityHomeRecyclerView.adapter = adapter
        adapter.clickItem = {
            Toast.makeText(this, "click item ${it.idOrder}", Toast.LENGTH_SHORT).show()
            Timber.d("click")
        }
    }

    private fun setFabButton() {
        binding.activityHomeExtendFab.setOnClickListener {
            Toast.makeText(this, "click fab", Toast.LENGTH_SHORT).show()
            Timber.d("click")
        }
    }
}
