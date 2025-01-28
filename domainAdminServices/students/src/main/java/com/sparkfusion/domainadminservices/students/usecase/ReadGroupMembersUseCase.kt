package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.ReadGroupMemberEntityMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupMembersUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadGroupMembersUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val readGroupMemberEntityMapper: ReadGroupMemberEntityMapper
): IReadGroupMembersUseCase {

    override suspend fun readGroupMembers(groupId: Int): Answer<List<ReadGroupMemberModel>> {
        return studentsServiceRepository.readGroupMembers(groupId)
            .suspendMap { list ->
                list.map { readGroupMemberEntityMapper.map(it) }
            }
    }
}