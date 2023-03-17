package br.com.vaniala.omie.ui.singup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.vaniala.omie.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
    }
}
