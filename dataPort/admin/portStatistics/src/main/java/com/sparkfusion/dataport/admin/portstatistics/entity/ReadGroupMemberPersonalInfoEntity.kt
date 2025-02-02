package com.sparkfusion.dataport.admin.portstatistics.entity

data class ReadGroupMemberPersonalInfoEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String
)
