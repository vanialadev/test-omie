package br.com.vaniala.omie.data.utils

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
class DatastoreManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    suspend fun addToDatastore(key: Preferences.Key<String>, value: String) {
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

    fun observeKeyValue(key: Preferences.Key<String>): Flow<String?> {
        return context.dataStore.data.map { settings ->
            settings[key]
        }
    }

    suspend fun removeFromDatastore(key: Preferences.Key<String>) {
        context.dataStore.edit { settings ->
            settings.remove(key)
        }
    }
}
