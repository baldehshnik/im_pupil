package com.sparkfusion.dataport.admin.portstudents.entity

data class ReadGroupWithMembersEntity(
    val id: Int,
    val name: String,
    val course: Int,
    val members: List<ReadGroupMemberInfoEntity>
)
