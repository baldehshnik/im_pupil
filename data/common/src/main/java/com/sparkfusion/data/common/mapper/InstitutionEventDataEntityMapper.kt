package com.sparkfusion.data.common.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.common.entity.InstitutionEventDataEntity
import com.sparkfusion.dataPort.common.portinstitutionevent.InstitutionEventEntity
import javax.inject.Inject

internal class InstitutionEventDataEntityMapper @Inject constructor(
): Mapper<InstitutionEventDataEntity, InstitutionEventEntity> {

    override suspend fun map(input: InstitutionEventDataEntity): InstitutionEventEntity = with(input) {
        InstitutionEventEntity(id, title, description, image, duration, type)
    }
}




























