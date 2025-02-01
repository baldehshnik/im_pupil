package com.sparkfusion.dataport.admin.portmagazine.entity

data class ReadGroupMemberEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String
)
