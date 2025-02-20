package com.sparkfusion.domainpupilservices.magazine.di

import com.sparkfusion.domainpupilservices.magazine.usecase.ReadGroupMembersUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.ReadPassOfGroupMemberUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.ReadPassesUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.ReadPupilAccountUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.ReadScheduleWithPassesUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.ReadTodayScheduleUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.ReadWeekStatisticsUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.UpdatePassOfGroupMemberUseCase
import com.sparkfusion.domainpupilservices.magazine.usecase.UpdatePassesUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadGroupMembersUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPassOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPassesUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPupilAccountUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadScheduleWithPassesUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadTodayScheduleUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadWeekStatisticsUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IUpdatePassOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IUpdatePassesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadPassesUseCaseToIReadPassesUseCase(readPassesUseCase: ReadPassesUseCase): IReadPassesUseCase

    @Binds
    fun bindReadPassOfGroupMemberUseCaseToIReadPassOfGroupMemberUseCase(readPassOfGroupMemberUseCase: ReadPassOfGroupMemberUseCase): IReadPassOfGroupMemberUseCase

    @Binds
    fun bindReadPupilAccountUseCaseToIReadPupilAccountUseCase(readPupilAccountUseCase: ReadPupilAccountUseCase): IReadPupilAccountUseCase

    @Binds
    fun bindReadWeekStatisticsUseCaseToIReadWeekStatisticsUseCase(readWeekStatisticsUseCase: ReadWeekStatisticsUseCase): IReadWeekStatisticsUseCase

    @Binds
    fun bindUpdatePassesUseCaseToIUpdatePassesUseCase(updatePassesUseCase: UpdatePassesUseCase): IUpdatePassesUseCase

    @Binds
    fun bindUpdatePassOfGroupMemberUseCaseToIUpdatePassOfGroupMemberUseCase(updatePassOfGroupMemberUseCase: UpdatePassOfGroupMemberUseCase): IUpdatePassOfGroupMemberUseCase

    @Binds
    fun bindReadTodayScheduleUseCaseToIReadTodayScheduleUseCase(readTodayScheduleUseCase: ReadTodayScheduleUseCase) : IReadTodayScheduleUseCase

    @Binds
    fun bindReadGroupMembersUseCaseToIReadGroupMembersUseCase(readGroupMembersUseCase: ReadGroupMembersUseCase): IReadGroupMembersUseCase

    @Binds
    fun bindReadScheduleWithPassesUseCaseToIReadScheduleWithPassesUseCase(readScheduleWithPassesUseCase: ReadScheduleWithPassesUseCase): IReadScheduleWithPassesUseCase
}























