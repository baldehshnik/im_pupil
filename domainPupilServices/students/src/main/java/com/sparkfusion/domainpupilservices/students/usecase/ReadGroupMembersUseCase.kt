package com.sparkfusion.domainpupilservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstudents.IStudentRepository
import com.sparkfusion.domainpupilservices.students.mapper.GroupMemberEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstudents.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstudents.usecase.IReadGroupMembersUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMembersUseCase @Inject constructor(
    private val studentsRepository: IStudentRepository,
    private val groupMemberEntityMapper: GroupMemberEntityMapper
) : IReadGroupMembersUseCase {

    override suspend fun readGroupMembers(): Answer<List<GroupMemberModel>> {
        return studentsRepository.readGroupMembers()
            .suspendMap { list ->
                list.map {
                    groupMemberEntityMapper.map(it)
                }
            }
    }
}
















