package br.com.vaniala.omie.ui.singup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.vaniala.omie.R
import br.com.vaniala.omie.databinding.ActivitySingUpBinding
import br.com.vaniala.omie.domain.model.UserModel
import br.com.vaniala.omie.extensions.goTo
import br.com.vaniala.omie.ui.singin.SingInActivity
import br.com.vaniala.omie.ui.singup.viewmodel.SingUpViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SingUpActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySingUpBinding.inflate(layoutInflater)
    }

    private val viewModel: SingUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = resources.getString(R.string.sing_up_user)

        setSingUpButton()

        lifecycleScope.launch {
            singUpSuccess(this)
            singUpError(this)
            stateFields(this)
        }
    }

    private fun stateFields(coroutineScope: CoroutineScope) {
        viewModel.state.onEach { state ->
            Timber.d(state.toString())
            binding.activitySingUpTextInputLayoutEmail.error = if (state.isEmailValid) {
                null
            } else {
                resources.getString(R.string.invalid_email)
            }
            binding.activitySingUpTextInputLayoutPassword.error = if (state.isPasswordValid) {
                null
            } else {
                resources.getString(R.string.invalid_password)
            }
        }.launchIn(coroutineScope)
    }

    private fun singUpSuccess(coroutineScope: CoroutineScope) {
        viewModel.singUpSuccess.onEach {
            Timber.d("Register success!")
            val snackbar = Snackbar.make(
                binding.root,
                resources.getString(R.string.register_success),
                BaseTransientBottomBar.LENGTH_SHORT,
            )
            snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    goToSingIn()
                }
            })
                .show()
        }.launchIn(coroutineScope)
    }

    private fun singUpError(coroutineScope: CoroutineScope) {
        viewModel.error.onEach {
            Timber.d(it.toString())
            Snackbar.make(
                binding.root,
                resources.getString(R.string.sing_up_error),
                BaseTransientBottomBar.LENGTH_SHORT,
            ).show()
        }.launchIn(coroutineScope)
    }

    private fun setSingUpButton() {
        binding.activitySingUpButtonSingUp.setOnClickListener {
            Timber.d("click")
            val user = createUser()
            viewModel.singUp(user)
        }
    }

    private fun createUser(): UserModel {
        return UserModel(
            name = binding.activitySingUpName.text.toString(),
            email = binding.activitySingUpEmail.text.toString(),
            password = binding.activitySingUpPassword.text.toString(),
        )
    }

    private fun goToSingIn() {
        finish()
        goTo(SingInActivity::class.java)
    }
}
