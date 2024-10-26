package com.sparkfusion.dataPort.common.faculty

import com.google.gson.annotations.SerializedName

data class FacultyDataEntity(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
