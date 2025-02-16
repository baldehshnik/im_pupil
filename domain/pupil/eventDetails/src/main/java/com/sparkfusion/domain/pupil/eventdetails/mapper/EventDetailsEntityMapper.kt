package com.sparkfusion.domain.pupil.eventdetails.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.porteventdetails.EventDetailsEntity
import com.sparkfusion.domain.pupil.port.porteventdetails.model.EventDetailsModel
import javax.inject.Inject

internal class EventDetailsEntityMapper @Inject constructor(
): Mapper<EventDetailsEntity, EventDetailsModel> {

    override suspend fun map(input: EventDetailsEntity): EventDetailsModel = with(input) {
        EventDetailsModel(id, title, description, image, duration, type)
    }
}