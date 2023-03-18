package br.com.vaniala.omie.data.di

import br.com.vaniala.omie.data.datasource.local.LocalDataSource
import br.com.vaniala.omie.data.datasource.local.LocalDataSourceImpl
import br.com.vaniala.omie.database.dao.UserDao
import br.com.vaniala.omie.domain.repository.UserRepository
import br.com.vaniala.omie.domain.usecase.IsLoggedUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(
        userDao: UserDao,
    ): LocalDataSource {
        return LocalDataSourceImpl(userDao)
    }

    @Provides
    fun provideIsLoggedUserCase(
        userRepository: UserRepository,
    ): IsLoggedUserCase {
        return IsLoggedUserCase(userRepository)
    }
}
