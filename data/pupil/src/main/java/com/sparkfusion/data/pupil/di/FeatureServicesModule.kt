package com.sparkfusion.data.pupil.di

import com.sparkfusion.data.pupil.source.AboutApiService
import com.sparkfusion.data.pupil.source.AccountApiService
import com.sparkfusion.data.pupil.source.AuthApiService
import com.sparkfusion.data.pupil.source.GroupMemberApiService
import com.sparkfusion.data.pupil.source.InstitutionEventApiService
import com.sparkfusion.data.pupil.source.MagazineApiService
import com.sparkfusion.data.pupil.source.PracticeApiService
import com.sparkfusion.data.pupil.source.ScheduleApiService
import com.sparkfusion.data.pupil.source.SectionApiService
import com.sparkfusion.data.pupil.source.SessionApiService
import com.sparkfusion.data.pupil.source.StatisticsApiService
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

    @Singleton
    @Provides
    fun providePracticeApiService(retrofit: Retrofit): PracticeApiService {
        return retrofit.create(PracticeApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAboutApiService(retrofit: Retrofit): AboutApiService {
        return retrofit.create(AboutApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSectionApiService(retrofit: Retrofit): SectionApiService {
        return retrofit.create(SectionApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGroupMemberApiService(retrofit: Retrofit): GroupMemberApiService {
        return retrofit.create(GroupMemberApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSessionApiService(retrofit: Retrofit): SessionApiService {
        return retrofit.create(SessionApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideScheduleApiService(retrofit: Retrofit): ScheduleApiService {
        return retrofit.create(ScheduleApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideStatisticsApiService(retrofit: Retrofit): StatisticsApiService {
        return retrofit.create(StatisticsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMagazineApiService(retrofit: Retrofit): MagazineApiService {
        return retrofit.create(MagazineApiService::class.java)
    }
}



























