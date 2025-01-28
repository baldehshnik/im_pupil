package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer

interface IDeleteGroupUseCase {

    suspend fun deleteGroupById(id: Int): Answer<Unit>
}