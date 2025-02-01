package com.sparkfusion.domainadminservices.magazine.di

import com.sparkfusion.domainadminservices.magazine.usecase.ReadFacultiesUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadGroupByNamePartUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadGroupMembersUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadLessonsWithPassStatusUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadPassOfGroupMemberUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadPassesUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadTodayScheduleUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.ReadWeekStatisticsUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.UpdatePassOfGroupMemberUseCase
import com.sparkfusion.domainadminservices.magazine.usecase.UpdatePassesOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadFacultiesUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadGroupByNamePartUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadGroupMembersUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadLessonsWithPassStatusUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadPassOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadPassesUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadTodayScheduleUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadWeekStatisticsUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IUpdatePassOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IUpdatePassesOfGroupMemberUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadGroupMembersUseCaseToIReadGroupMembersUseCase(readGroupMembersUseCase: ReadGroupMembersUseCase): IReadGroupMembersUseCase

    @Binds
    fun bindReadLessonsWithPassStatusUseCaseToIReadLessonsWithPassStatusUseCase(readLessonsWithPassStatusUseCase: ReadLessonsWithPassStatusUseCase): IReadLessonsWithPassStatusUseCase

    @Binds
    fun bindReadPassesUseCaseToIReadPassesUseCase(readPassesUseCase: ReadPassesUseCase): IReadPassesUseCase

    @Binds
    fun bindReadPassOfGroupMemberUseCaseToIReadPassOfGroupMemberUseCase(readPassOfGroupMemberUseCase: ReadPassOfGroupMemberUseCase): IReadPassOfGroupMemberUseCase

    @Binds
    fun bindReadTodayScheduleUseCaseToIReadTodayScheduleUseCase(readTodayScheduleUseCase: ReadTodayScheduleUseCase): IReadTodayScheduleUseCase

    @Binds
    fun bindReadWeekStatisticsUseCaseToIReadWeekStatisticsUseCase(readWeekStatisticsUseCase: ReadWeekStatisticsUseCase): IReadWeekStatisticsUseCase

    @Binds
    fun bindUpdatePassesOfGroupMemberUseCaseToIUpdatePassesOfGroupMemberUseCase(updatePassesOfGroupMemberUseCase: UpdatePassesOfGroupMemberUseCase): IUpdatePassesOfGroupMemberUseCase

    @Binds
    fun bindUpdatePassOfGroupMemberUseCaseToIUpdatePassOfGroupMemberUseCase(updatePassOfGroupMemberUseCase: UpdatePassOfGroupMemberUseCase): IUpdatePassOfGroupMemberUseCase

    @Binds
    fun bindReadFacultiesUseCaseToIReadFacultiesUseCase(readFacultiesUseCase: ReadFacultiesUseCase): IReadFacultiesUseCase

    @Binds
    fun bindReadGroupByNamePartUseCaseToIReadGroupByNameUseCase(readGroupByNamePartUseCase: ReadGroupByNamePartUseCase): IReadGroupByNamePartUseCase
}



























