package com.sparkfusion.domain.admin.post.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portinstitutionevent.UpdateInstitutionEventEntity
import com.sparkfusion.domain.admin.port.portpost.UpdateInstitutionEventModel
import javax.inject.Inject

class UpdateInstitutionEventModelMapper @Inject constructor(
): Mapper<UpdateInstitutionEventModel, UpdateInstitutionEventEntity> {

    override suspend fun map(input: UpdateInstitutionEventModel): UpdateInstitutionEventEntity = with(input) {
        UpdateInstitutionEventEntity(id, title, description, image, duration, type)
    }
}