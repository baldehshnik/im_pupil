package com.sparkfusion.portdomainservices.admin.portstudents.model

data class ReadGroupWithMembersModel(
    val id: Int,
    val name: String,
    val course: Int,
    val members: List<ReadGroupMemberInfoModel>
)
