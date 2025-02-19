package com.sparkfusion.domainpupilservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstudents.IStudentRepository
import com.sparkfusion.domainpupilservices.students.mapper.GroupMemberEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstudents.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstudents.usecase.IReadGroupMemberByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberByIdUseCase @Inject constructor(
    private val studentsRepository: IStudentRepository,
    private val groupMemberEntityMapper: GroupMemberEntityMapper
): IReadGroupMemberByIdUseCase {

    override suspend fun readGroupMemberById(id: Int): Answer<GroupMemberModel> {
        return studentsRepository.readGroupMemberById(id)
            .suspendMap { groupMemberEntityMapper.map(it) }
    }
}



















