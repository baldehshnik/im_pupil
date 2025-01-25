package com.sparkfusion.domain.admin.port.portconfirmation

data class AdminModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val accessMode: Int,
    val icon: String?
)
