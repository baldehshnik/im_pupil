package com.sparkfusion.domain.pupil.home.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.porthome.ReadInstitutionEventEntity
import com.sparkfusion.domain.pupil.port.porthome.model.ReadInstitutionEventModel
import javax.inject.Inject

internal class ReadInstitutionEventEntityMapper @Inject constructor(
): Mapper<ReadInstitutionEventEntity, ReadInstitutionEventModel> {

    override suspend fun map(input: ReadInstitutionEventEntity): ReadInstitutionEventModel = with(input) {
        ReadInstitutionEventModel(id, title, description, image, duration, type)
    }
}