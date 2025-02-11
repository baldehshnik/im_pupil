package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.EducationPlaceEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.EducationPlaceModel
import javax.inject.Inject

internal class EducationPlaceEntityMapper @Inject constructor(
): Mapper<EducationPlaceEntity, EducationPlaceModel> {

    override suspend fun map(input: EducationPlaceEntity): EducationPlaceModel = with(input) {
        EducationPlaceModel(institutionName, facultyName, groupName)
    }
}