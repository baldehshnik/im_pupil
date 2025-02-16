package com.sparkfusion.dataport.pupil.portaccount.entity

data class ReadPupilAccountEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String,
    val pupil: PupilEntity,
    val groupInfo: GroupInfoEntity
)
