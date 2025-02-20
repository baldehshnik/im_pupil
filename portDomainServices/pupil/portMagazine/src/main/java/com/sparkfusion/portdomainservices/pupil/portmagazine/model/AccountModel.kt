package com.sparkfusion.portdomainservices.pupil.portmagazine.model

data class AccountModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String
)
