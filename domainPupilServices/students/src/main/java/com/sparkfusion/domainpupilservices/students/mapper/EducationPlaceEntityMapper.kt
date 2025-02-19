package com.sparkfusion.domainpupilservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portstudents.EducationPlaceEntity
import com.sparkfusion.portdomainservices.pupil.portstudents.model.EducationPlaceModel
import javax.inject.Inject

internal class EducationPlaceEntityMapper @Inject constructor(
): Mapper<EducationPlaceEntity, EducationPlaceModel> {

    override suspend fun map(input: EducationPlaceEntity): EducationPlaceModel = with(input) {
        EducationPlaceModel(institutionName, facultyName, groupName)
    }
}