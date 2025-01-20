package com.sparkfusion.domain.admin.port.portadmindetails

import com.sparkfusion.core.common.result.Answer

interface IDeleteAdminUseCase {

    suspend fun deleteAdminById(id: Int): Answer<Unit>
}