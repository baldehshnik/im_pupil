package com.sparkfusion.dataport.pupil.portsection

data class SectionEntity(
    val id: Int,
    val title: String,
    val trainer: String,
    val price: Boolean,
    val gender: Int,
    val description: String,
    val icon: String,
    val fromCourse: Int,
    val toCourse: Int
)
