package com.sparkfusion.dataPort.admin.portaccount

data class AdminEntity(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val accessMode: Int,
    val icon: String?
)
