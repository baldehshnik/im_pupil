package com.sparkfusion.domainadminservices.exam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.domainadminservices.exam.mapper.AddExamModelMapper
import com.sparkfusion.portdomainservices.admin.portexam.model.AddExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.ICreateExamUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateExamUseCase @Inject constructor(
    private val examRepository: IExamRepository,
    private val addExamModelMapper: AddExamModelMapper
): ICreateExamUseCase {

    override suspend fun createExam(exam: AddExamModel): Answer<Unit> {
        return examRepository.createExam(addExamModelMapper.map(exam))
    }
}



















