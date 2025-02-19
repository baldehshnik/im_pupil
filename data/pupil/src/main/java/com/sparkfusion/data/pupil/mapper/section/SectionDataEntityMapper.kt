package com.sparkfusion.data.pupil.mapper.section

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.section.SectionDataEntity
import com.sparkfusion.dataport.pupil.portsection.SectionEntity
import javax.inject.Inject

internal class SectionDataEntityMapper @Inject constructor(
): Mapper<SectionDataEntity, SectionEntity> {

    override suspend fun map(input: SectionDataEntity): SectionEntity = with(input) {
        SectionEntity(id, title, trainer, price, gender, description, icon, fromCourse, toCourse)
    }
}