package com.sparkfusion.domainadminservices.about.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.common.portabout.ReadAboutEntity
import com.sparkfusion.portdomainservices.admin.portabout.AboutModel
import javax.inject.Inject

internal class ReadAboutEntityMapper @Inject constructor(
) : Mapper<ReadAboutEntity, AboutModel> {

    override suspend fun map(input: ReadAboutEntity): AboutModel = with(input) {
        AboutModel(id, description, icon)
    }
}


















