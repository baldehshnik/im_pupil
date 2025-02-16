package com.sparkfusion.data.pupil.entity.account

internal data class GroupInfoDataEntity(
    val institutionAbbreviation: String,
    val institutionName: String,
    val institutionAddress: String,
    val institutionPhone: String,
    val specialityName: String,
    val groupName: String,
    val groupMembersCount: Int
)
