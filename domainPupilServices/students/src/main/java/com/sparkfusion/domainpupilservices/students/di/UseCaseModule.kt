package com.sparkfusion.domainpupilservices.students.di

import com.sparkfusion.domainpupilservices.students.usecase.ReadGroupMemberByIdUseCase
import com.sparkfusion.domainpupilservices.students.usecase.ReadGroupMembersUseCase
import com.sparkfusion.portdomainservices.pupil.portstudents.usecase.IReadGroupMemberByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portstudents.usecase.IReadGroupMembersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadGroupMembersUseCaseToIReadGroupMembersUseCase(readGroupMembersUseCase: ReadGroupMembersUseCase): IReadGroupMembersUseCase

    @Binds
    fun bindReadGroupMemberByIdUseCaseToIReadGroupMemberByIdUseCase(readGroupMemberByIdUseCase: ReadGroupMemberByIdUseCase): IReadGroupMemberByIdUseCase
}























