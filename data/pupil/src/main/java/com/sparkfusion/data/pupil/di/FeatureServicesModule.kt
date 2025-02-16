package com.sparkfusion.data.pupil.di

import com.sparkfusion.data.pupil.source.AccountApiService
import com.sparkfusion.data.pupil.source.AuthApiService
import com.sparkfusion.data.pupil.source.InstitutionEventApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FeatureServicesModule {

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideInstitutionEventApiService(retrofit: Retrofit): InstitutionEventApiService {
        return retrofit.create(InstitutionEventApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAccountApiService(retrofit: Retrofit): AccountApiService {
        return retrofit.create(AccountApiService::class.java)
    }
}



























