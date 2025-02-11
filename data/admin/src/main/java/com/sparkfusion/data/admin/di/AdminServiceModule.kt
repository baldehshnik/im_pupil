package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.source.AdminAboutApiService
import com.sparkfusion.data.admin.source.ExamApiService
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.admin.source.GroupMemberApiService
import com.sparkfusion.data.admin.source.PracticeApiService
import com.sparkfusion.data.admin.source.SectionApiService
import com.sparkfusion.data.admin.source.schedule.LessonApiService
import com.sparkfusion.data.admin.source.schedule.PassApiService
import com.sparkfusion.data.admin.source.schedule.ScheduleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AdminServiceModule {

    @Singleton
    @Provides
    internal fun provideAdminAboutApiService(retrofit: Retrofit): AdminAboutApiService {
        return retrofit.create(AdminAboutApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideGroupApiService(retrofit: Retrofit): GroupApiService {
        return retrofit.create(GroupApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideGroupMemberApiService(retrofit: Retrofit): GroupMemberApiService {
        return retrofit.create(GroupMemberApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideExamApiService(retrofit: Retrofit): ExamApiService {
        return retrofit.create(ExamApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideLessonApiService(retrofit: Retrofit): LessonApiService {
        return retrofit.create(LessonApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideScheduleApiService(retrofit: Retrofit): ScheduleApiService {
        return retrofit.create(ScheduleApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun providePassApiService(retrofit: Retrofit): PassApiService {
        return retrofit.create(PassApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideSectionApiService(retrofit: Retrofit): SectionApiService {
        return retrofit.create(SectionApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun providePracticeApiService(retrofit: Retrofit): PracticeApiService {
        return retrofit.create(PracticeApiService::class.java)
    }
}

























