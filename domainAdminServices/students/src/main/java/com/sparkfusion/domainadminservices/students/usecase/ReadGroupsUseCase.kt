package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.GroupEntityMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupsUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val groupEntityMapper: GroupEntityMapper
) : IReadGroupsUseCase {

    override suspend fun readGroupBySpeciality(specialityId: Int): Answer<List<GroupModel>> {
        return studentsServiceRepository.readGroupBySpeciality(specialityId)
            .suspendMap { list ->
                list.map { groupEntityMapper.map(it) }
            }
    }
}