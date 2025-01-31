package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.FacultyEntityMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.FacultyModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadFacultiesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadFacultiesUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val facultyEntityMapper: FacultyEntityMapper
): IReadFacultiesUseCase {

    override suspend fun readFaculties(): Answer<List<FacultyModel>> {
        return scheduleRepository.readFaculties()
            .suspendMap { list -> list.map { facultyEntityMapper.map(it) } }
    }
}