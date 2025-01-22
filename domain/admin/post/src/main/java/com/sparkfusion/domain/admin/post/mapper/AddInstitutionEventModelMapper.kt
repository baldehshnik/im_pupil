package com.sparkfusion.domain.admin.post.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portinstitutionevent.AddInstitutionEventEntity
import com.sparkfusion.domain.admin.port.portpost.AddInstitutionEventModel
import javax.inject.Inject

class AddInstitutionEventModelMapper @Inject constructor(
): Mapper<AddInstitutionEventModel, AddInstitutionEventEntity> {

    override suspend fun map(input: AddInstitutionEventModel): AddInstitutionEventEntity = with(input) {
        AddInstitutionEventEntity(title, description, duration, type)
    }
}