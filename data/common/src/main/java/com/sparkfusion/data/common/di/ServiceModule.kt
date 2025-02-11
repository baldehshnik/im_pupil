package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.service.AboutApiService
import com.sparkfusion.data.common.service.AppServicesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object ServiceModule {

    @Provides
    @Singleton
    internal fun provideAppServicesApiService(retrofit: Retrofit): AppServicesApiService {
        return retrofit.create(AppServicesApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideAboutApiService(retrofit: Retrofit): AboutApiService {
        return retrofit.create(AboutApiService::class.java)
    }
}























