package com.sparkfusion.portdomainservices.admin.portpractice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadListPracticeModel

interface IReadPracticesUseCase {

    suspend fun readPractices(): Answer<List<ReadListPracticeModel>>
}