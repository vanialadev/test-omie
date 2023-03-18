package br.com.vaniala.omie.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.databinding.ActivityHomeBinding
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.ui.home.viewmodel.HomeViewModel
import br.com.vaniala.omie.ui.singin.SingInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.logout.onEach {
                goTo(SingInActivity::class.java)
            }.launchIn(this)
        }
    }
}
