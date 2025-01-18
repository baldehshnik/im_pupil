package com.sparkfusion.data.common.entity

import com.sparkfusion.data.commonentity.CommonNewsInfoDataEntity

data class NewsInfoEntity(
    override val id: Int,
    override val title: String,
    override val imageUrl: String?,
    override val description: String?,
    override val guides: List<GuideEntity>
) : CommonNewsInfoDataEntity
