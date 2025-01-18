package com.sparkfusion.domain.admin.port.portauth

data class SignUpModel(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val password: String,
    val educationalInstitutionAbbreviation: String
)
