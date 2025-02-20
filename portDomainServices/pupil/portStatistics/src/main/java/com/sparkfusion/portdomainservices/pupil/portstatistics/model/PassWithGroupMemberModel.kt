package com.sparkfusion.portdomainservices.pupil.portstatistics.model

import java.time.Instant

data class PassWithGroupMemberModel(
    val id: Int,
    val date: Instant,
    val lesson: LessonModel,
    val groupMember: GroupMemberInfoModel
)
