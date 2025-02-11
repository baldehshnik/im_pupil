package com.sparkfusion.domain.admin.post.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.common.portinstitutionevent.InstitutionEventEntity
import com.sparkfusion.domain.admin.port.portpost.InstitutionEventModel
import javax.inject.Inject

internal class InstitutionEventEntityMapper @Inject constructor(
): Mapper<InstitutionEventEntity, InstitutionEventModel> {

    override suspend fun map(input: InstitutionEventEntity): InstitutionEventModel = with(input) {
        InstitutionEventModel(id, title, description, image, duration, type)
    }
}