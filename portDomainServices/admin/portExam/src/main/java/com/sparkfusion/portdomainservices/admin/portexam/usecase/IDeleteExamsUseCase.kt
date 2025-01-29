package com.sparkfusion.portdomainservices.admin.portexam.usecase

import com.sparkfusion.core.common.result.Answer

interface IDeleteExamsUseCase {

    suspend fun deleteExams(ids: List<Int>): Answer<Unit>
}