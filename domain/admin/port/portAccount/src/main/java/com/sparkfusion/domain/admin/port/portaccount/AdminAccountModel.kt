package com.sparkfusion.domain.admin.port.portaccount

data class AdminAccountModel(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val accessMode: Int,
    val icon: String?
)
