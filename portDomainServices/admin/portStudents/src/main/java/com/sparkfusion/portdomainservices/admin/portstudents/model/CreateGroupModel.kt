package com.sparkfusion.portdomainservices.admin.portstudents.model

data class CreateGroupModel(
    val specialityId: Int,
    val name: String,
    val course: Int,
    val groupMemberDtos: List<CreateGroupMemberModel>
)
