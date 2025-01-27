package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.service.AboutApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideAboutApiService(retrofit: Retrofit): AboutApiService {
        return retrofit.create(AboutApiService::class.java)
    }
}























