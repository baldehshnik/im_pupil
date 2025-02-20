package com.sparkfusion.dataport.pupil.portmagazine

import java.time.LocalDate

data class UpdatePassStatusEntity(
    val id: Int?,
    val groupMemberId: Int,
    val lessonId: Int,
    val date: LocalDate,
    val status: Int
)