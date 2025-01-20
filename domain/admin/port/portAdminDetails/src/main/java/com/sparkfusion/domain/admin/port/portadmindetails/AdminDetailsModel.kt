package com.sparkfusion.domain.admin.port.portadmindetails

data class AdminDetailsModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val accessMode: Int,
    val icon: String?
)
