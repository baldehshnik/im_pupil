package com.sparkfusion.dataport.admin.portsections.entity

data class UpdateSectionEntity(
    val id: Int,
    val title: String,
    val trainer: String,
    val price: Boolean,
    val gender: Int,
    val description: String,
    val fromCourse: Int?,
    val toCourse: Int?,
    val icon: String?
)
