package com.sparkfusion.portdomainservices.admin.portstudents.model

data class UpdateGroupModel(
    val id: Int,
    val course: Int,
    val groupMemberDtos: List<UpdateGroupMemberModel>
)
