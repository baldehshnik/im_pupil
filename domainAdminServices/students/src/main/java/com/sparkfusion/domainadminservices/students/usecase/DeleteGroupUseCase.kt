package com.sparkfusion.domainadminservices.students.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IDeleteGroupUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class DeleteGroupUseCase @Inject constructor(
    private val studentsServiceRepository: IStudentsServiceRepository
): IDeleteGroupUseCase {

    override suspend fun deleteGroupById(id: Int): Answer<Unit> {
        return studentsServiceRepository.deleteGroupById(id)
    }
}