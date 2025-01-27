package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.source.AdminAboutApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdminServiceModule {

    @Singleton
    @Provides
    fun provideAdminAboutApiService(retrofit: Retrofit): AdminAboutApiService {
        return retrofit.create(AdminAboutApiService::class.java)
    }
}

























