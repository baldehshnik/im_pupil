package com.sparkfusion.portdomainservices.admin.portpractice.model

data class ReadPracticeModel(
    val id: Int,
    val icon: String?,
    val payAbility: Boolean,
    val description: String,
    val workType: Int,
    val title: String,
    val website: String?,
    val informationBlocks: Set<InformationBlockModel>,
    val relocations: Set<RelocationModel>
)
