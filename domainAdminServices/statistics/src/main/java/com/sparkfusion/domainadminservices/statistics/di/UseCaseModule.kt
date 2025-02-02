package com.sparkfusion.domainadminservices.statistics.di

import com.sparkfusion.domainadminservices.statistics.usecase.ReadGroupByNamePartUseCase
import com.sparkfusion.domainadminservices.statistics.usecase.ReadGroupMemberPassesPerMonthUseCase
import com.sparkfusion.domainadminservices.statistics.usecase.ReadGroupMemberPassesPerSemesterUseCase
import com.sparkfusion.domainadminservices.statistics.usecase.ReadGroupMembersForStatisticsUseCase
import com.sparkfusion.domainadminservices.statistics.usecase.ReadPassesOfGroupPerMonthUseCase
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupByNamePartUseCase
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMemberPassesPerMonthUseCase
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMemberPassesPerSemesterUseCase
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMembersForStatisticsUseCase
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadPassesOfGroupPerMonthUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadGroupByNamePartUseCaseToIReadGroupByNamePartUseCase(readGroupByNamePartUseCase: ReadGroupByNamePartUseCase): IReadGroupByNamePartUseCase

    @Binds
    fun bindReadGroupMemberPassesPerMonthUseCaseToIReadGroupMemberPassesPerMonthUseCase(readGroupMemberPassesPerMonthUseCase: ReadGroupMemberPassesPerMonthUseCase): IReadGroupMemberPassesPerMonthUseCase

    @Binds
    fun bindReadGroupMemberPassesPerSemesterUseCaseToIReadGroupMemberPassesPerSemesterUseCase(readGroupMemberPassesPerSemesterUseCase: ReadGroupMemberPassesPerSemesterUseCase): IReadGroupMemberPassesPerSemesterUseCase

    @Binds
    fun bindReadGroupMembersForStatisticsUseCaseToIReadGroupMembersForStatisticsUseCase(readGroupMembersForStatisticsUseCase: ReadGroupMembersForStatisticsUseCase): IReadGroupMembersForStatisticsUseCase

    @Binds
    fun bindReadPassesOfGroupPerMonthUseCaseToIReadPassesOfGroupPerMonthUseCase(readPassesOfGroupPerMonthUseCase: ReadPassesOfGroupPerMonthUseCase): IReadPassesOfGroupPerMonthUseCase
}

























