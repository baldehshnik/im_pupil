package com.sparkfusion.dataport.pupil.portaccount.entity

data class GroupInfoEntity(
    val institutionAbbreviation: String,
    val institutionName: String,
    val institutionAddress: String,
    val institutionPhone: String,
    val specialityName: String,
    val groupName: String,
    val groupMembersCount: Int
)
