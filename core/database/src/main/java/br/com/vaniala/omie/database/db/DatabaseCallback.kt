package br.com.vaniala.omie.database.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.vaniala.omie.database.dao.ItemDao
import br.com.vaniala.omie.database.utils.itemsBD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
class DatabaseCallback @Inject constructor(
    private val provider: Provider<ItemDao>,
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        provider.get().insertAll(itemsBD)
    }
}
