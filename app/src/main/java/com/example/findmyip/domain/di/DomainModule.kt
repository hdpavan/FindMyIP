package com.example.findmyip.domain.di

import com.example.findmyip.Util.DefaultDispatchers
import com.example.findmyip.Util.DispatchersProvider
import com.example.findmyip.data.repository.RepositoryImpl
import com.example.findmyip.domain.repository.IRepository
import com.example.findmyip.domain.usecase.GetIPUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun getUseCae(repository: IRepository): GetIPUseCase {
        return GetIPUseCase(repository)
    }

    @Provides
    fun getDispatcher(): DispatchersProvider {
        return DefaultDispatchers()
    }
}