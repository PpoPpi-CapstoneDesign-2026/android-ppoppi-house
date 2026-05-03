package com.ppoppi.house.di

import com.ppoppi.house.domain.repository.AuthRepository
import com.ppoppi.house.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(impl: AuthRepositoryImpl): AuthRepository
}
