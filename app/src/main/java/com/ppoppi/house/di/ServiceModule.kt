package com.ppoppi.house.di

import com.ppoppi.house.data.service.AuthService
import com.ppoppi.house.data.service.DiagnosisService
import com.ppoppi.house.data.service.DiaryService
import com.ppoppi.house.data.service.DiseaseService
import com.ppoppi.house.data.service.HospitalService
import com.ppoppi.house.data.service.PetService
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
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideSymptomService(retrofit: Retrofit): SymptomService = retrofit.create(SymptomService::class.java)

    @Provides
    @Singleton
    fun provideHospitalService(retrofit: Retrofit): HospitalService = retrofit.create(HospitalService::class.java)

    @Provides
    @Singleton
    fun providePetService(retrofit: Retrofit): PetService = retrofit.create(PetService::class.java)

    @Provides
    @Singleton
    fun provideDiagnosisService(retrofit: Retrofit): DiagnosisService = retrofit.create(DiagnosisService::class.java)

    @Provides
    @Singleton
    fun provideDiseaseService(retrofit: Retrofit): DiseaseService = retrofit.create(DiseaseService::class.java)

    @Provides
    @Singleton
    fun provideDiaryService(retrofit: Retrofit): DiaryService = retrofit.create(DiaryService::class.java)
}
