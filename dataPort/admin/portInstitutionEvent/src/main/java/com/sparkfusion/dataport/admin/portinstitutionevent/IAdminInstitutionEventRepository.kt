package com.sparkfusion.dataport.admin.portinstitutionevent

import com.sparkfusion.core.common.result.Answer
import java.io.File

interface IAdminInstitutionEventRepository {

    suspend fun deleteEventById(id: Int): Answer<Unit>

    suspend fun addEvent(entity: AddInstitutionEventEntity, image: File): Answer<Unit>

    suspend fun updateEvent(entity: UpdateInstitutionEventEntity, image: File?): Answer<Unit>
}




















