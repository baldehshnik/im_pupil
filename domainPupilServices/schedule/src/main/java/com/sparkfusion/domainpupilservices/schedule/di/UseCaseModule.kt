package com.sparkfusion.domainpupilservices.schedule.di

import com.sparkfusion.domainpupilservices.schedule.usecase.ReadScheduleWithLessonsUseCase
import com.sparkfusion.domainpupilservices.schedule.usecase.ReadSchedulesUseCase
import com.sparkfusion.portdomainservices.pupil.portschedule.IReadScheduleWithLessonsUseCase
import com.sparkfusion.portdomainservices.pupil.portschedule.IReadSchedulesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadSchedulesUseCaseToIReadSchedulesUseCase(readSchedulesUseCase: ReadSchedulesUseCase): IReadSchedulesUseCase

    @Binds
    fun bindReadScheduleWithLessonsUseCaseToIReadScheduleWithLessonsUseCase(
        readScheduleWithLessonsUseCase: ReadScheduleWithLessonsUseCase
    ): IReadScheduleWithLessonsUseCase
}























