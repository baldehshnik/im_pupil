package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.UpdateGroupModelMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IUpdateGroupUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateGroupUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val updateGroupModelMapper: UpdateGroupModelMapper
) : IUpdateGroupUseCase {

    override suspend fun updateGroup(updateInstitutionGroupDto: UpdateGroupModel): Answer<Unit> {
        return studentsServiceRepository.updateGroup(
            updateGroupModelMapper.map(updateInstitutionGroupDto)
        )
    }
}