package com.sparkfusion.domainpupilservices.section.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portsection.SectionEntity
import com.sparkfusion.portdomainservices.pupil.portsection.SectionModel
import javax.inject.Inject

internal class SectionEntityMapper @Inject constructor(
): Mapper<SectionEntity, SectionModel> {

    override suspend fun map(input: SectionEntity): SectionModel = with(input) {
        SectionModel(id, title, trainer, price, gender, description, icon, fromCourse, toCourse)
    }
}