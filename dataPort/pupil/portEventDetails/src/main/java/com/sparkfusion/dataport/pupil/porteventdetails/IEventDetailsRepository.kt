package com.sparkfusion.dataport.pupil.porteventdetails

import com.sparkfusion.core.common.result.Answer

interface IEventDetailsRepository {

    suspend fun readEventById(id: Int): Answer<EventDetailsEntity>
}




























