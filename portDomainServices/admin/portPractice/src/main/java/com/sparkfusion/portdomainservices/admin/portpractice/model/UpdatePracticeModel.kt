package com.sparkfusion.portdomainservices.admin.portpractice.model

data class UpdatePracticeModel(
    val id: Int?,
    val icon: String?,
    val payAbility: Boolean,
    val description: String,
    val workType: Int,
    val title: String,
    val website: String,
    val informationBlocks: Set<UpdateInformationBlockModel>,
    val relocations: Set<UpdateRelocationModel>
)
