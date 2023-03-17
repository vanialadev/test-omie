package br.com.vaniala.omie.database.di

import android.content.Context
import androidx.room.Room
import br.com.vaniala.omie.database.db.OmieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */

private const val NAME_DATABASE = "omie.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Volatile
    private lateinit var db: OmieDatabase

    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): OmieDatabase {
        if (DatabaseModule::db.isInitialized) {
            return db
        }
        db = Room.databaseBuilder(
            context,
            OmieDatabase::class.java,
            NAME_DATABASE,
        ).build()
        return db
    }
}
