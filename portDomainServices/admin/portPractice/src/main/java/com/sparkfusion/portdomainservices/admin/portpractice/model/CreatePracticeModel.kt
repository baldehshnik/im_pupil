package com.sparkfusion.portdomainservices.admin.portpractice.model

data class CreatePracticeModel(
    val payAbility: Boolean,
    val description: String,
    val workType: Int,
    val title: String,
    val website: String,
    val informationBlocks: Set<InformationBlockModel>,
    val relocations: Set<RelocationModel>
)
