package com.sparkfusion.domainadminservices.sections.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portsections.entity.UpdateSectionEntity
import com.sparkfusion.portdomainservices.admin.portsections.model.UpdateSectionModel
import javax.inject.Inject

internal class UpdateSectionModelMapper @Inject constructor(
): Mapper<UpdateSectionModel, UpdateSectionEntity> {

    override suspend fun map(input: UpdateSectionModel): UpdateSectionEntity = with(input) {
        UpdateSectionEntity(id, title, trainer, price, gender, description, fromCourse, toCourse, icon)
    }
}