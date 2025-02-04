package com.sparkfusion.dataport.admin.portpractice.entity

data class UpdatePracticeEntity(
    val id: Int?,
    val icon: String?,
    val payAbility: Boolean,
    val description: String,
    val workType: Int,
    val title: String,
    val website: String,
    val informationBlocks: Set<UpdateInformationBlockEntity>,
    val relocations: Set<UpdateRelocationEntity>
)
