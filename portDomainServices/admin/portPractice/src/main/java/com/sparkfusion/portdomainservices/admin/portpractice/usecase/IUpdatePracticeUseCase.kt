package com.sparkfusion.portdomainservices.admin.portpractice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portpractice.model.UpdatePracticeModel
import java.io.File

interface IUpdatePracticeUseCase {

    suspend fun updatePractice(practice: UpdatePracticeModel, image: File?): Answer<Unit>
}