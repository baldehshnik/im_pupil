package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.CreateGroupModelMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.ICreateGroupUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateGroupUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val createGroupModelMapper: CreateGroupModelMapper
): ICreateGroupUseCase {

    override suspend fun createGroup(createInstitutionGroupDto: CreateGroupModel): Answer<Unit> {
        return studentsServiceRepository.createGroup(createGroupModelMapper.map(createInstitutionGroupDto))
    }
}