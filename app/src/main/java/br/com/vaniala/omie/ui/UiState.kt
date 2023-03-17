package br.com.vaniala.omie.ui

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
