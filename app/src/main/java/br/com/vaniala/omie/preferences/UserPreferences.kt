package br.com.vaniala.omie.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "logged_user")

val loggedUserPreferences = stringPreferencesKey("loggedUser")
