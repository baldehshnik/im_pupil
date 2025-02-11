package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.FacultyModel
import javax.inject.Inject

internal class FacultyEntityMapper @Inject constructor(
): Mapper<CommonFacultyDataEntity, FacultyModel> {

    override suspend fun map(input: CommonFacultyDataEntity): FacultyModel = with(input) {
        FacultyModel(id, name, abbreviation)
    }
}