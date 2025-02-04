package com.sparkfusion.dataport.admin.portpractice.entity

data class CreatePracticeEntity(
    val payAbility: Boolean,
    val description: String,
    val workType: Int,
    val title: String,
    val website: String,
    val informationBlocks: Set<InformationBlockEntity>,
    val relocations: Set<RelocationEntity>
)
