package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonEducationPlaceDataEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.EducationPlaceModel
import javax.inject.Inject

class EducationPlaceEntityMapper @Inject constructor(
): Mapper<CommonEducationPlaceDataEntity, EducationPlaceModel> {

    override suspend fun map(input: CommonEducationPlaceDataEntity): EducationPlaceModel = with(input) {
        EducationPlaceModel(institutionName, facultyName, groupName)
    }
}