package com.ppoppi.house.di

import com.ppoppi.house.data.repository.AuthRepositoryImpl
import com.ppoppi.house.data.repository.DiagnosisRepositoryImpl
import com.ppoppi.house.data.repository.DiaryRepositoryImpl
import com.ppoppi.house.data.repository.DiseaseRepositoryImpl
import com.ppoppi.house.data.repository.HospitalRepositoryImpl
import com.ppoppi.house.data.repository.PetsRepositoryImpl
import com.ppoppi.house.data.repository.SymptomRepositoryImpl
import com.ppoppi.house.domain.repository.AuthRepository
import com.ppoppi.house.domain.repository.DiagnosisRepository
import com.ppoppi.house.domain.repository.DiaryRepository
import com.ppoppi.house.domain.repository.DiseaseRepository
import com.ppoppi.house.domain.repository.HospitalRepository
import com.ppoppi.house.domain.repository.PetsRepository
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

    @Binds
    @Singleton
    abstract fun bindPetRepository(impl: PetsRepositoryImpl): PetsRepository

    @Binds
    @Singleton
    abstract fun bindDiagnosisRepository(impl: DiagnosisRepositoryImpl): DiagnosisRepository

    @Binds
    @Singleton
    abstract fun bindDiseaseRepository(impl: DiseaseRepositoryImpl): DiseaseRepository

    @Binds
    @Singleton
    abstract fun bindDiaryRepository(impl: DiaryRepositoryImpl): DiaryRepository
}
