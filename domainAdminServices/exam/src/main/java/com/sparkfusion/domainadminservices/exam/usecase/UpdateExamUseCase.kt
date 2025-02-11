package com.sparkfusion.domainadminservices.exam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.domainadminservices.exam.mapper.UpdateExamModelMapper
import com.sparkfusion.portdomainservices.admin.portexam.model.UpdateExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IUpdateExamUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateExamUseCase @Inject constructor(
    private val examRepository: IExamRepository,
    private val updateExamModelMapper: UpdateExamModelMapper
): IUpdateExamUseCase {

    override suspend fun updateExam(exam: UpdateExamModel): Answer<Unit> {
        return examRepository.updateExam(updateExamModelMapper.map(exam))
    }
}





















