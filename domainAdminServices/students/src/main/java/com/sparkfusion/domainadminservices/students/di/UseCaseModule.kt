package com.sparkfusion.domainadminservices.students.di

import com.sparkfusion.domainadminservices.students.usecase.CreateGroupUseCase
import com.sparkfusion.domainadminservices.students.usecase.DeleteGroupUseCase
import com.sparkfusion.domainadminservices.students.usecase.MakeStudentAPrefectUseCase
import com.sparkfusion.domainadminservices.students.usecase.ReadFacultyUseCase
import com.sparkfusion.domainadminservices.students.usecase.ReadGroupMemberByIdUseCase
import com.sparkfusion.domainadminservices.students.usecase.ReadGroupMembersUseCase
import com.sparkfusion.domainadminservices.students.usecase.ReadGroupWithMembersUseCase
import com.sparkfusion.domainadminservices.students.usecase.ReadGroupsUseCase
import com.sparkfusion.domainadminservices.students.usecase.ReadSpecialityUseCase
import com.sparkfusion.domainadminservices.students.usecase.UpdateGroupUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.ICreateGroupUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IDeleteGroupUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IMakeStudentAPrefectUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadFacultyUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupMemberByIdUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupMembersUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupWithMembersUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupsUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadSpecialityUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IUpdateGroupUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindCreateGroupUseCaseToICreateGroupUseCase(createGroupUseCase: CreateGroupUseCase): ICreateGroupUseCase

    @Binds
    fun bindDeleteGroupUseCaseToIDeleteGroupUseCase(deleteGroupUseCase: DeleteGroupUseCase): IDeleteGroupUseCase

    @Binds
    fun bindMakeStudentAPrefectUseCaseToIMakeStudentAPrefectUseCase(makeStudentAPrefectUseCase: MakeStudentAPrefectUseCase): IMakeStudentAPrefectUseCase

    @Binds
    fun bindReadFacultyUseCaseToIReadFacultyUseCase(readFacultyUseCase: ReadFacultyUseCase): IReadFacultyUseCase

    @Binds
    fun bindReadGroupMemberByIdUseCaseToIReadGroupMemberByIdUseCase(readGroupMemberByIdUseCase: ReadGroupMemberByIdUseCase): IReadGroupMemberByIdUseCase

    @Binds
    fun bindReadGroupMembersUseCaseToIReadGroupMembersUseCase(readGroupMembersUseCase: ReadGroupMembersUseCase): IReadGroupMembersUseCase

    @Binds
    fun bindReadGroupsUseCaseToIReadGroupsUseCase(readGroupsUseCase: ReadGroupsUseCase): IReadGroupsUseCase

    @Binds
    fun bindReadSpecialityUseCaseToIReadSpecialityUseCase(readSpecialityUseCase: ReadSpecialityUseCase): IReadSpecialityUseCase

    @Binds
    fun bindUpdateGroupUseCaseToIUpdateGroupUseCase(updateGroupUseCase: UpdateGroupUseCase): IUpdateGroupUseCase

    @Binds
    fun bindReadGroupWithMembersUseCaseToIReadGroupWithMembersUseCase(readGroupWithMembersUseCase: ReadGroupWithMembersUseCase): IReadGroupWithMembersUseCase
}





















