package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.SpecialityEntityMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.SpecialityModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadSpecialityUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadSpecialityUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val specialityEntityMapper: SpecialityEntityMapper
) : IReadSpecialityUseCase {

    override suspend fun readSpecialitiesByFaculty(facultyId: Int): Answer<List<SpecialityModel>> {
        return studentsServiceRepository.readSpecialitiesByFaculty(facultyId)
            .suspendMap { list ->
                list.map { specialityEntityMapper.map(it) }
            }
    }
}