package com.sparkfusion.domainadminservices.schedule.di

import com.sparkfusion.domainadminservices.schedule.usecase.ClearScheduleStatusUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.CreateScheduleUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.MakeScheduleCurrentUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.ReadFacultiesUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.ReadGroupByNamePartUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.ReadLessonsUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.ReadScheduleByGroupIdUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.ReadScheduleWithLessonsUseCase
import com.sparkfusion.domainadminservices.schedule.usecase.UpdateScheduleUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IClearScheduleStatusUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.ICreateScheduleUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IMakeScheduleCurrentUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadFacultiesUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadGroupByNamePartUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadLessonsUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadScheduleByGroupIdUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadScheduleWithLessonsUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IUpdateScheduleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindClearScheduleStatusUseCaseToIClearScheduleStatusUseCase(clearScheduleStatusUseCase: ClearScheduleStatusUseCase): IClearScheduleStatusUseCase

    @Binds
    fun bindCreateScheduleUseCaseToICreateScheduleUseCase(createScheduleUseCase: CreateScheduleUseCase): ICreateScheduleUseCase

    @Binds
    fun bindMakeScheduleCurrentUseCaseToIMakeScheduleCurrentUseCase(makeScheduleCurrentUseCase: MakeScheduleCurrentUseCase): IMakeScheduleCurrentUseCase

    @Binds
    fun bindReadFacultiesUseCaseToIReadFacultiesUseCase(readFacultiesUseCase: ReadFacultiesUseCase): IReadFacultiesUseCase

    @Binds
    fun bindReadLessonsUseCaseToIReadLessonsUseCase(readLessonsUseCase: ReadLessonsUseCase): IReadLessonsUseCase

    @Binds
    fun bindReadScheduleByGroupIdUseCaseToIReadScheduleByGroupIdUseCase(readScheduleByGroupIdUseCase: ReadScheduleByGroupIdUseCase): IReadScheduleByGroupIdUseCase

    @Binds
    fun bindReadGroupByNamePartUseCaseToIReadGroupByNamePartUseCase(readGroupByNamePartUseCase: ReadGroupByNamePartUseCase): IReadGroupByNamePartUseCase

    @Binds
    fun bindReadScheduleWithLessonsUseCaseToIReadScheduleWithLessonsUseCase(readScheduleWithLessonsUseCase: ReadScheduleWithLessonsUseCase): IReadScheduleWithLessonsUseCase

    @Binds
    fun bindUpdateScheduleUseCaseToUpdateScheduleUseCase(updateScheduleUseCase: UpdateScheduleUseCase): IUpdateScheduleUseCase
}





















