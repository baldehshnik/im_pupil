package com.sparkfusion.portdomainservices.pupil.portpractice

data class PracticeInfoModel(
    val id: Int,
    val icon: String,
    val payAbility: Boolean,
    val description: String,
    val workType: Int,
    val title: String,
    val website: String
)
