package com.sparkfusion.portdomainservices.admin.portexam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portexam.model.UpdateExamModel

interface IUpdateExamUseCase {

    suspend fun updateExam(exam: UpdateExamModel): Answer<Unit>
}