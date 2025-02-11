package com.sparkfusion.domainadminservices.sections.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portsections.entity.ReadSectionEntity
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel
import javax.inject.Inject

internal class ReadSectionEntityMapper @Inject constructor(
): Mapper<ReadSectionEntity, ReadSectionModel> {

    override suspend fun map(input: ReadSectionEntity): ReadSectionModel = with(input) {
        ReadSectionModel(id, title, trainer, price, gender, description, icon, fromCourse, toCourse)
    }
}