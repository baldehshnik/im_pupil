package com.sparkfusion.portdomainservices.admin.portexam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel

interface IReadExamByIdUseCase {

    suspend fun readExamById(id: Int): Answer<ReadExamModel>
}