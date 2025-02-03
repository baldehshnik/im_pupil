package com.sparkfusion.domainadminservices.sections.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portsections.entity.CreateSectionEntity
import com.sparkfusion.portdomainservices.admin.portsections.model.CreateSectionModel
import javax.inject.Inject

class CreateSectionModelMapper @Inject constructor(
): Mapper<CreateSectionModel, CreateSectionEntity> {

    override suspend fun map(input: CreateSectionModel): CreateSectionEntity = with(input) {
        CreateSectionEntity(title, trainer, price, gender, description, fromCourse, toCourse)
    }
}