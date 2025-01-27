package com.sparkfusion.dataport.common.portabout

import com.sparkfusion.data.commonentity.about.CommonReadAboutDataEntity

data class ReadAboutEntity(
    override val id: Int,
    override val description: String,
    override val icon: String?
): CommonReadAboutDataEntity
