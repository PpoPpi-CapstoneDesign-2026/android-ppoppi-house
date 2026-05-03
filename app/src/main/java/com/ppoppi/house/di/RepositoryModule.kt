package com.ppoppi.house.di

import com.ppoppi.house.data.repository.AuthRepositoryImpl
import com.ppoppi.house.data.repository.HospitalRepositoryImpl
import com.ppoppi.house.data.repository.SymptomRepositoryImpl
import com.ppoppi.house.domain.repository.AuthRepository
import com.ppoppi.house.domain.repository.HospitalRepository
import com.ppoppi.house.domain.repository.SymptomRepository
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

    @Binds
    @Singleton
    abstract fun bindSymptomRepository(impl: SymptomRepositoryImpl): SymptomRepository

    @Binds
    @Singleton
    abstract fun bindHospitalRepository(impl: HospitalRepositoryImpl): HospitalRepository
}
