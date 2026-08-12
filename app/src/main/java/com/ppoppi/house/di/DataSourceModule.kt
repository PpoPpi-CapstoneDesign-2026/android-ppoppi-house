package com.ppoppi.house.di

import com.ppoppi.house.data.datasource.TokenDataSource
import com.ppoppi.house.data.datasource.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindTokenDataSource(impl: TokenDataSourceImpl): TokenDataSource
}
