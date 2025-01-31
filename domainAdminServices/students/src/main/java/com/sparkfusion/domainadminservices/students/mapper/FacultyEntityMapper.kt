package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.FacultyModel
import javax.inject.Inject

class FacultyEntityMapper @Inject constructor(
) : Mapper<CommonFacultyDataEntity, FacultyModel> {

    override suspend fun map(input: CommonFacultyDataEntity): FacultyModel = with(input) {
        FacultyModel(id, name, abbreviation)
    }
}