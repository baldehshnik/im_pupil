package com.sparkfusion.domain.pupil.port.portaccount.model

data class GroupInfoModel(
    val institutionAbbreviation: String,
    val institutionName: String,
    val institutionAddress: String,
    val institutionPhone: String,
    val specialityName: String,
    val groupName: String,
    val groupMembersCount: Int
)
