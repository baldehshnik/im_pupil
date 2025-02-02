package com.sparkfusion.dataport.admin.portstatistics.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

data class ReadFullPassWithGroupMemberEntity(
    val id: Int,
    @JsonAdapter(InstantAdapter::class) val date: Instant,
    val lesson: ReadLessonEntity,
    val groupMember: ReadGroupMemberPersonalInfoEntity
)
