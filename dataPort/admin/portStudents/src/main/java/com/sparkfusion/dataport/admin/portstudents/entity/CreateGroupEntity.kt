package com.sparkfusion.dataport.admin.portstudents.entity

data class CreateGroupEntity(
    val specialityId: Int,
    val name: String,
    val course: Int,
    val groupMemberDtos: List<CreateGroupMemberEntity>
)
