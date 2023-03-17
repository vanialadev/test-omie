package br.com.vaniala.omie.ui.singin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.databinding.ActivitySingInBinding
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.preferences.dataStore
import br.com.vaniala.omie.preferences.loggedUserPreferences
import br.com.vaniala.omie.ui.home.HomeActivity
import br.com.vaniala.omie.ui.singup.SingUpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingInActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySingInBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSingInButton()
        setSingUpButton()
    }

    private fun setSingInButton() {
        binding.activitySingInButtonSingIn.setOnClickListener {
            authenticate()
        }
    }

    private fun setSingUpButton() {
        binding.activitySingInButtonSingUp.setOnClickListener {
            gotoSingUp()
        }
    }

    private fun gotoSingUp() {
        Intent(this, SingUpActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun authenticate(email: String? = null, password: String? = null) {
        lifecycleScope.launch {
            dataStore.edit { preferences ->
                preferences[loggedUserPreferences] = "v@v.com"
            }
            goTo(HomeActivity::class.java)
            finish()
        }
    }
}
