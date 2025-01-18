package com.sparkfusion.data.commonentity

interface CommonNewsInfoDataEntity {
    val id: Int
    val title: String
    val imageUrl: String?
    val description: String?
    val guides: List<CommonNewsGuideDataEntity>
}






























