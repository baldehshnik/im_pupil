package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.faculty.FacultyApiService
import com.sparkfusion.data.common.service.InstitutionEventApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FacultyModule {

    @Singleton
    @Provides
    fun provideFacultyApiService(retrofit: Retrofit): FacultyApiService {
        return retrofit.create(FacultyApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideInstitutionEventApiService(retrofit: Retrofit): InstitutionEventApiService {
        return retrofit.create(InstitutionEventApiService::class.java)
    }
}
