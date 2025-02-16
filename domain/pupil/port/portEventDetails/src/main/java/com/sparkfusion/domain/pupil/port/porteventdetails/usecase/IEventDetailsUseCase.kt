package com.sparkfusion.domain.pupil.port.porteventdetails.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.porteventdetails.model.EventDetailsModel

interface IEventDetailsUseCase {

    suspend fun readEventById(id: Int): Answer<EventDetailsModel>
}