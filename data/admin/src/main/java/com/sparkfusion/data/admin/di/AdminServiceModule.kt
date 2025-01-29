package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.source.AdminAboutApiService
import com.sparkfusion.data.admin.source.ExamApiService
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.admin.source.GroupMemberApiService
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

    @Singleton
    @Provides
    fun provideGroupApiService(retrofit: Retrofit): GroupApiService {
        return retrofit.create(GroupApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGroupMemberApiService(retrofit: Retrofit): GroupMemberApiService {
        return retrofit.create(GroupMemberApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideExamApiService(retrofit: Retrofit): ExamApiService {
        return retrofit.create(ExamApiService::class.java)
    }
}

























