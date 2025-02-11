package com.sparkfusion.domainadminservices.exam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portexam.DeleteExamsEntity
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IDeleteExamsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class DeleteExamsUseCase @Inject constructor(
    private val examRepository: IExamRepository
): IDeleteExamsUseCase {

    override suspend fun deleteExams(ids: List<Int>): Answer<Unit> {
        return examRepository.deleteExams(DeleteExamsEntity(ids))
    }
}































