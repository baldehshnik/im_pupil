package com.sparkfusion.dataport.admin.portsections.entity

data class CreateSectionEntity(
    val title: String,
    val trainer: String,
    val price: Boolean,
    val gender: Int,
    val description: String,
    val fromCourse: Int?,
    val toCourse: Int?
)
