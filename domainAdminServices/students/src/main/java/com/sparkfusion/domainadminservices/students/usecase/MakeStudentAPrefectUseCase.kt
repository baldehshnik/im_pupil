package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IMakeStudentAPrefectUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class MakeStudentAPrefectUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository
): IMakeStudentAPrefectUseCase {

    override suspend fun makeStudentAPrefect(id: Int): Answer<Unit> {
        return studentsServiceRepository.makeStudentAPrefect(id)
    }
}