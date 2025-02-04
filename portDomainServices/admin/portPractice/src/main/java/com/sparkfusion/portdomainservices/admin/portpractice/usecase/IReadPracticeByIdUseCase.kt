package com.sparkfusion.portdomainservices.admin.portpractice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadPracticeModel

interface IReadPracticeByIdUseCase {

    suspend fun readPracticeById(id: Int): Answer<ReadPracticeModel>
}