package br.com.vaniala.omie.ui

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
sealed class Action {
    object FetchData : Action()
    object Clicked : Action()
    object Refresh : Action()
}
