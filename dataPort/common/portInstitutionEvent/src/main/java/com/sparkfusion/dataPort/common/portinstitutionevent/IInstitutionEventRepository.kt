package com.sparkfusion.dataPort.common.portinstitutionevent

import com.sparkfusion.core.common.result.Answer

interface IInstitutionEventRepository {

    suspend fun readInstitutionEvents(institutionId: Int): Answer<List<InstitutionEventEntity>>

    suspend fun readInstitutionEvent(eventId: Int): Answer<InstitutionEventEntity>
}
























