package com.sparkfusion.domain.admin.port.portaccount

data class InstitutionModel(
    val id: Int,
    val name: String,
    val abbreviation: String,
    val type: Int,
    val address: String?,
    val phone: String?
)
