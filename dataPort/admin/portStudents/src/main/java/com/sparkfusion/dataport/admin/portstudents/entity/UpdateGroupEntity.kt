package com.sparkfusion.dataport.admin.portstudents.entity

data class UpdateGroupEntity(
    val id: Int,
    val course: Int,
    val groupMemberDtos: List<UpdateGroupMemberEntity>
)