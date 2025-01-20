package com.sparkfusion.domain.admin.port.portadmindetails

import com.sparkfusion.core.common.result.Answer

interface IReadAdminDetailsUseCase {

    suspend fun readAdminDetailsById(id: Int): Answer<AdminDetailsModel>
}