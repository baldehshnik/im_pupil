package com.sparkfusion.dataport.admin.portadmindetails

data class AdminDetailsEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val accessMode: Int,
    val icon: String?
)
