package com.sparkfusion.portdomainservices.admin.portpractice.usecase

import com.sparkfusion.core.common.result.Answer

interface IDeletePracticeByIdUseCase {

    suspend fun deletePracticeById(id: Int): Answer<Unit>
}