package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.GroupEntityMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadGroupByNamePartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupByNamePartUseCase @Inject constructor(
    private val institutionRepository: IAdminInstitutionRepository,
    private val scheduleRepository: IScheduleRepository,
    private val groupEntityMapper: GroupEntityMapper
) : IReadGroupByNamePartUseCase {

    override suspend fun readGroupByNamePart(namePart: String): Answer<List<GroupModel>> {
        var error: ImPupilException? = null
        val result = institutionRepository.readInstitutionOfAdmin()
            .onFailure { error = it }

        if (error != null) return Answer.Failure(error!!)
        return scheduleRepository.readGroupByNamePart(result.unwrap().id, namePart)
            .suspendMap { list -> list.map { groupEntityMapper.map(it) } }
    }
}










