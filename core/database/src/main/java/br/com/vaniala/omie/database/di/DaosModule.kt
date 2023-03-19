package br.com.vaniala.omie.database.di

import br.com.vaniala.omie.database.dao.ItemDao
import br.com.vaniala.omie.database.dao.OrderDao
import br.com.vaniala.omie.database.dao.OrderWithItemDao
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

    @Provides
    fun providesOrderDao(
        db: OmieDatabase,
    ): OrderDao = db.orderDao()

    @Provides
    fun providesItemDao(
        db: OmieDatabase,
    ): ItemDao = db.itemDao()

    @Provides
    fun providesOrderWithItemDao(
        db: OmieDatabase,
    ): OrderWithItemDao = db.orderWithItemDao()
}
