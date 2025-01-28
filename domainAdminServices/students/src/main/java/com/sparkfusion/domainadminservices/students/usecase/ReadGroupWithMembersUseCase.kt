package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.ReadGroupWithMembersEntityMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupWithMembersModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupWithMembersUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadGroupWithMembersUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val readGroupWithMembersEntityMapper: ReadGroupWithMembersEntityMapper
): IReadGroupWithMembersUseCase {

    override suspend fun readGroupWithMembers(id: Int): Answer<ReadGroupWithMembersModel> {
        return studentsServiceRepository.readGroupWithMembersById(id)
            .suspendMap { readGroupWithMembersEntityMapper.map(it) }
    }
}
















