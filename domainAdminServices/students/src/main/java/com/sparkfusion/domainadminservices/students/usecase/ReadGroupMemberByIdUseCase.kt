package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.ReadGroupMemberEntityMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupMemberByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberByIdUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val readGroupMemberEntityMapper: ReadGroupMemberEntityMapper
) : IReadGroupMemberByIdUseCase {

    override suspend fun readGroupMemberById(id: Int): Answer<ReadGroupMemberModel> {
        return studentsServiceRepository.readGroupMemberById(id)
            .suspendMap { readGroupMemberEntityMapper.map(it) }
    }
}