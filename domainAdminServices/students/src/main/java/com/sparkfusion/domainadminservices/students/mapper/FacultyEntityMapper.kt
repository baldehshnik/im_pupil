package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.FacultyEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.FacultyModel
import javax.inject.Inject

class FacultyEntityMapper @Inject constructor(
): Mapper<FacultyEntity, FacultyModel> {

    override suspend fun map(input: FacultyEntity): FacultyModel = with(input) {
        FacultyModel(id, name, abbreviation)
    }
}