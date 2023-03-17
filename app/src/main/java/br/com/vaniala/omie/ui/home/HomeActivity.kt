package br.com.vaniala.omie.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.databinding.ActivityHomeBinding
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.preferences.dataStore
import br.com.vaniala.omie.preferences.loggedUserPreferences
import br.com.vaniala.omie.ui.singin.SingInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        lifecycleScope.launch {
            checkLoggedUser()
        }
    }

    private suspend fun checkLoggedUser() {
        dataStore.data.collect { preferences: Preferences ->
            preferences[loggedUserPreferences]?.let { email ->
                Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show()
            } ?: goTo(SingInActivity::class.java)
        }
    }
}
