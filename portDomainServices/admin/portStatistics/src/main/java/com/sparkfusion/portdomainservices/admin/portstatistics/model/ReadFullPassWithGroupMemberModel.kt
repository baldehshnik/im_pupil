package com.sparkfusion.portdomainservices.admin.portstatistics.model

import java.time.Instant

data class ReadFullPassWithGroupMemberModel(
    val id: Int,
    val date: Instant,
    val lesson: ReadLessonModel,
    val groupMember: ReadGroupMemberPersonalInfoModel
)
