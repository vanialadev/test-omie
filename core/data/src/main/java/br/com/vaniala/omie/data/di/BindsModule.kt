package br.com.vaniala.omie.data.di

import br.com.vaniala.omie.data.repository.ItemRepositoryImpl
import br.com.vaniala.omie.data.repository.OrderRepositoryImpl
import br.com.vaniala.omie.data.repository.UserRepositoryImpl
import br.com.vaniala.omie.domain.repository.ItemRepository
import br.com.vaniala.omie.domain.repository.OrderRepository
import br.com.vaniala.omie.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
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
interface BindsModule {

    @Binds
    @Singleton
    fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    @Singleton
    fun bindsItemRepository(
        itemRepositoryImpl: ItemRepositoryImpl,
    ): ItemRepository

    @Binds
    @Singleton
    fun bindsOrderRepository(
        orderRepositoryImpl: OrderRepositoryImpl,
    ): OrderRepository
}
