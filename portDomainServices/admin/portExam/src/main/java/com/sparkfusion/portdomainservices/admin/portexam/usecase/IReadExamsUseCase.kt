package com.sparkfusion.portdomainservices.admin.portexam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel

interface IReadExamsUseCase {

    suspend fun readExams(groupId: Int): Answer<List<ReadExamModel>>
}