package com.sparkfusion.data.pupil.mapper.about

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.about.AboutDataEntity
import com.sparkfusion.dataport.pupil.portabout.AboutEntity
import javax.inject.Inject

internal class AboutDataEntityMapper @Inject constructor(
): Mapper<AboutDataEntity, AboutEntity> {

    override suspend fun map(input: AboutDataEntity): AboutEntity = with(input) {
        AboutEntity(id, description, icon)
    }
}