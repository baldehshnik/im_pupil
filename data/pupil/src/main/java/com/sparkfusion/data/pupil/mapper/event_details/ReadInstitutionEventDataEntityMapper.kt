package com.sparkfusion.data.pupil.mapper.event_details

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.ReadInstitutionEventDataEntity
import com.sparkfusion.dataport.pupil.porteventdetails.EventDetailsEntity
import javax.inject.Inject

internal class ReadInstitutionEventDataEntityMapper @Inject constructor(
): Mapper<ReadInstitutionEventDataEntity, EventDetailsEntity> {

    override suspend fun map(input: ReadInstitutionEventDataEntity): EventDetailsEntity = with(input) {
        EventDetailsEntity(id, title, description, image, duration, type)
    }
}