package com.sparkfusion.data.common.entity.about

import com.sparkfusion.data.commonentity.about.CommonReadAboutDataEntity

data class ReadAboutDataEntity(
    override val id: Int,
    override val description: String,
    override val icon: String?
): CommonReadAboutDataEntity