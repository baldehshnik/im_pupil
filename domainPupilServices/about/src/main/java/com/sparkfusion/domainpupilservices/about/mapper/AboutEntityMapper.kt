package com.sparkfusion.domainpupilservices.about.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portabout.AboutEntity
import com.sparkfusion.portdomainservices.pupil.portabout.model.AboutModel
import javax.inject.Inject

internal class AboutEntityMapper @Inject constructor(
): Mapper<AboutEntity, AboutModel> {

    override suspend fun map(input: AboutEntity): AboutModel = with(input) {
        AboutModel(id, description, icon)
    }
}