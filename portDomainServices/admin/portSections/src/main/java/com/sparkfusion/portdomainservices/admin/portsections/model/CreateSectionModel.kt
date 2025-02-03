package com.sparkfusion.portdomainservices.admin.portsections.model

data class CreateSectionModel(
    val title: String,
    val trainer: String,
    val price: Boolean,
    val gender: Int,
    val description: String,
    val fromCourse: Int?,
    val toCourse: Int?
)
