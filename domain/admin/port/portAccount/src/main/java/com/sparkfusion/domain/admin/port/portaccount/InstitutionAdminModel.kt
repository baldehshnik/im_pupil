package com.sparkfusion.domain.admin.port.portaccount

data class InstitutionAdminModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val accessMode: Int,
    val icon: String?
)
