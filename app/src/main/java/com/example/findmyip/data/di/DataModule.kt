package com.example.findmyip.data.di

import com.example.findmyip.Util.Constant
import com.example.findmyip.data.network.APIService
import com.example.findmyip.data.repository.RepositoryImpl
import com.example.findmyip.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun getService(): APIService {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }

    @Provides
    fun getRepository(apiService: APIService): IRepository {
        return RepositoryImpl(apiService)
    }
}