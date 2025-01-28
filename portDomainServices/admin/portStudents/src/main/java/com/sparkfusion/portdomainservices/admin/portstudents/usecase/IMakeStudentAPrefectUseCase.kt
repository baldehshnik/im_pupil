package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer

interface IMakeStudentAPrefectUseCase {

    suspend fun makeStudentAPrefect(id: Int): Answer<Unit>
}