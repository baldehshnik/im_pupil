package com.sparkfusion.portdomainservices.admin.portexam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portexam.model.AddExamModel

interface ICreateExamUseCase {

    suspend fun createExam(exam: AddExamModel): Answer<Unit>
}