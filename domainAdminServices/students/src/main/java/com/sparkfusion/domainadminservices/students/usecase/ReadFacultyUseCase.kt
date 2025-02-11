package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.domainadminservices.students.mapper.FacultyEntityMapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.FacultyModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadFacultyUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadFacultyUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository,
    private val facultyEntityMapper: FacultyEntityMapper
): IReadFacultyUseCase {

    override suspend fun readFaculties(): Answer<List<FacultyModel>> {
        return studentsServiceRepository.readFaculties()
            .suspendMap { list ->
                list.map { facultyEntityMapper.map(it) }
            }
    }
}