package com.sparkfusion.data.common.entity

import com.google.gson.annotations.SerializedName
import com.sparkfusion.data.commonentity.CommonNewsDataEntity

data class NewsEntity(

    @SerializedName("id")
    override val id: Int,

    @SerializedName("title")
    override val title: String,

    @SerializedName("image")
    override val imageUrl: String?,

    @SerializedName("description")
    override val description: String?

): CommonNewsDataEntity















