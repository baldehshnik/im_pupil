package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.source.AdminInstitutionEventApiService
import com.sparkfusion.data.admin.source.ConfirmationApiService
import com.sparkfusion.data.admin.source.InstitutionApiService
import com.sparkfusion.data.admin.source.NotificationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InstitutionModule {

    @Provides
    @Singleton
    internal fun provideInstitutionApiService(retrofit: Retrofit): InstitutionApiService {
        return retrofit.create(InstitutionApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideAdminInstitutionEventApiService(retrofit: Retrofit): AdminInstitutionEventApiService {
        return retrofit.create(AdminInstitutionEventApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideNotificationApiService(retrofit: Retrofit): NotificationApiService {
        return retrofit.create(NotificationApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideConfirmationApiService(retrofit: Retrofit): ConfirmationApiService {
        return retrofit.create(ConfirmationApiService::class.java)
    }
}

























