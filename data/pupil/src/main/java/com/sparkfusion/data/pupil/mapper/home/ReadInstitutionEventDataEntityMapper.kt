package com.sparkfusion.data.pupil.mapper.home

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.ReadInstitutionEventDataEntity
import com.sparkfusion.dataport.pupil.porthome.ReadInstitutionEventEntity
import javax.inject.Inject

internal class ReadInstitutionEventDataEntityMapper @Inject constructor(
): Mapper<ReadInstitutionEventDataEntity, ReadInstitutionEventEntity> {

    override suspend fun map(input: ReadInstitutionEventDataEntity): ReadInstitutionEventEntity = with(input) {
        ReadInstitutionEventEntity(id, title, description, image, duration, type)
    }
}

























