package br.com.vaniala.omie.data.di

import br.com.vaniala.omie.data.datasource.local.LocalDataSource
import br.com.vaniala.omie.data.datasource.local.LocalItemDataSourceImpl
import br.com.vaniala.omie.data.datasource.local.LocalLoginDataSourceImpl
import br.com.vaniala.omie.data.datasource.local.LocalOrderDataSourceImpl
import br.com.vaniala.omie.database.dao.ItemDao
import br.com.vaniala.omie.database.dao.OrderDao
import br.com.vaniala.omie.database.dao.OrderWithItemDao
import br.com.vaniala.omie.database.dao.UserDao
import br.com.vaniala.omie.domain.repository.UserRepository
import br.com.vaniala.omie.domain.usecase.login.IsLoggedUserCase
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
    ): LocalDataSource.Login {
        return LocalLoginDataSourceImpl(userDao)
    }

    @Singleton
    @Provides
    fun providesItemDataSource(
        itemDao: ItemDao,
    ): LocalDataSource.Item {
        return LocalItemDataSourceImpl(itemDao)
    }

    @Singleton
    @Provides
    fun providesOrderDataSource(
        orderDao: OrderDao,
        itemDao: ItemDao,
        orderWithItemDao: OrderWithItemDao,
    ): LocalDataSource.Order {
        return LocalOrderDataSourceImpl(orderDao, orderWithItemDao)
    }

    @Provides
    fun provideIsLoggedUserCase(
        userRepository: UserRepository,
    ): IsLoggedUserCase {
        return IsLoggedUserCase(userRepository)
    }
}
