package br.com.vaniala.omie.database.di

import br.com.vaniala.omie.database.dao.UserDao
import br.com.vaniala.omie.database.db.OmieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesUserDao(
        db: OmieDatabase,
    ): UserDao = db.userDao()
}
