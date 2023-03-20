package br.com.vaniala.omie.ui.singin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.ActivitySingInBinding
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.ui.home.HomeActivity
import br.com.vaniala.omie.ui.singin.viewmodel.SingInViewModel
import br.com.vaniala.omie.ui.singup.SingUpActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SingInActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySingInBinding.inflate(layoutInflater)
    }

    private val viewModel: SingInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSingInButton()
        setSingUpButton()

        lifecycleScope.launch {
            singInSuccess(this)
            stateFields(this)
            singInError(this)
        }
    }

    private fun singInSuccess(coroutineScope: CoroutineScope) {
        viewModel.navigation.onEach {
            goToHome()
        }.launchIn(coroutineScope)
    }

    private fun stateFields(coroutineScope: CoroutineScope) {
        viewModel.state.onEach { state ->
            Timber.d(state.toString())
            binding.activitySingInTextInputLayoutEmail.error = if (state.isEmailValid) {
                null
            } else {
                resources.getString(R.string.invalid_email)
            }
            binding.activitySingInTextInputLayoutPassword.error = if (state.isPasswordValid) {
                null
            } else {
                resources.getString(R.string.invalid_password)
            }
        }.launchIn(coroutineScope)
    }

    private fun singInError(coroutineScope: CoroutineScope) {
        viewModel.error.onEach {
            Timber.d(it.toString())
            Snackbar.make(
                binding.root,
                resources.getString(R.string.sing_in_error),
                BaseTransientBottomBar.LENGTH_SHORT,
            ).show()
        }.launchIn(coroutineScope)
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
        goTo(SingUpActivity::class.java)
    }
    private fun goToHome() {
        finish()
        goTo(HomeActivity::class.java)
    }

    private fun authenticate(email: String? = null, password: String? = null) {
        val email =
            email ?: binding.activitySingInTextInputLayoutEmail.editText?.text.toString()
        val password =
            password ?: binding.activitySingInTextInputLayoutPassword.editText?.text.toString()
        viewModel.authenticate(email, password)
    }
}
