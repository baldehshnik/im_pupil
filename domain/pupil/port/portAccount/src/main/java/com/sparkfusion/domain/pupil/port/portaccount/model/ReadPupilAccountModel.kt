package com.sparkfusion.domain.pupil.port.portaccount.model

data class ReadPupilAccountModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String,
    val pupil: PupilModel,
    val groupInfo: GroupInfoModel
)
