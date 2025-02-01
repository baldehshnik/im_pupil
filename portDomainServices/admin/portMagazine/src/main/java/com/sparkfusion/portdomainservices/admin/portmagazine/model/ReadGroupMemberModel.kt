package com.sparkfusion.portdomainservices.admin.portmagazine.model

data class ReadGroupMemberModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String
)
