package br.com.vaniala.omie.extensions

import androidx.core.util.PatternsCompat

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 18/03/23.
 *
 */
private const val MINIMAL_LENGTH = 7

fun String.isValidEmail(): Boolean =
    !isEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean = this.length > MINIMAL_LENGTH
