package com.ppoppi.house.di

import com.ppoppi.house.data.service.AuthService
import com.ppoppi.house.data.service.SymptomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideSymptomApiService(retrofit: Retrofit): SymptomService =
        retrofit.create(SymptomService::class.java)
}
