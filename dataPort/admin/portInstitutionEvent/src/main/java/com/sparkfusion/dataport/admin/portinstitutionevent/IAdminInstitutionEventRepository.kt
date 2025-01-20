package com.sparkfusion.dataport.admin.portinstitutionevent

import com.sparkfusion.core.common.result.Answer

interface IAdminInstitutionEventRepository {

    suspend fun deleteEventById(id: Int): Answer<Unit>
}