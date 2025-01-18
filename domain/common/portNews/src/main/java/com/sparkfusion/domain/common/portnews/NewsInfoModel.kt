package com.sparkfusion.domain.common.portnews

data class NewsInfoModel(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val description: String?,
    val guides: List<NewsGuideModel>
)
