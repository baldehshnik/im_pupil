package com.sparkfusion.data.pupil.entity.account

internal data class ReadPupilAccountDataEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String,
    val pupil: PupilDataEntity,
    val groupInfo: GroupInfoDataEntity
)
