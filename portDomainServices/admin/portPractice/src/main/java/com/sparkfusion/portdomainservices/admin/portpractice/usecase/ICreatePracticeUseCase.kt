package com.sparkfusion.portdomainservices.admin.portpractice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portpractice.model.CreatePracticeModel
import java.io.File

interface ICreatePracticeUseCase {

    suspend fun createPractice(practice: CreatePracticeModel, image: File): Answer<Unit>
}