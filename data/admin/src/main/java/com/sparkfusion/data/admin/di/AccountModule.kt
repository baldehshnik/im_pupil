package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.source.AccountApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Provides
    @Singleton
    fun provideAccountApiService(retrofit: Retrofit): AccountApiService {
        return retrofit.create(AccountApiService::class.java)
    }
}




















