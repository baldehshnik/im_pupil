package com.sparkfusion.data.pupil.mapper.students

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.students.EducationPlaceDataEntity
import com.sparkfusion.dataport.pupil.portstudents.EducationPlaceEntity
import javax.inject.Inject

internal class EducationPlaceDataEntityMapper @Inject constructor(
): Mapper<EducationPlaceDataEntity, EducationPlaceEntity> {

    override suspend fun map(input: EducationPlaceDataEntity): EducationPlaceEntity = with(input) {
        EducationPlaceEntity(institutionName, facultyName, groupName)
    }
}