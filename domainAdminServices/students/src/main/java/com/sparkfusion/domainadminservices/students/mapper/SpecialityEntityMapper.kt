package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.SpecialityEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.SpecialityModel
import javax.inject.Inject

internal class SpecialityEntityMapper @Inject constructor(
): Mapper<SpecialityEntity, SpecialityModel> {

    override suspend fun map(input: SpecialityEntity): SpecialityModel = with(input) {
        SpecialityModel(id, name, abbreviation)
    }
}