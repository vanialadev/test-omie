package br.com.vaniala.omie.data.utils

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
const val DATASTORE_LOGGED_NAME = "logged_user"
const val DATASTORE_LOGGED_EMAIL = "logged_user_email"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_LOGGED_NAME)

val DATASTORE_LOGGED_EMAIL_KEY = stringPreferencesKey(DATASTORE_LOGGED_EMAIL)
