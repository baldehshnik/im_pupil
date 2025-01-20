package com.sparkfusion.dataPort.admin.portaccount

data class InstitutionAdminEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val accessMode: Int,
    val icon: String?
)
