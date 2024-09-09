package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.service.NewsImPupilApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideNewsImPupilApiService(retrofit: Retrofit): NewsImPupilApiService {
        return retrofit.create(NewsImPupilApiService::class.java)
    }
}