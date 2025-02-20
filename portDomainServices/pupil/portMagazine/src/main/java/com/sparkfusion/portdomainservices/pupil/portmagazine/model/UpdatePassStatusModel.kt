package com.sparkfusion.portdomainservices.pupil.portmagazine.model

import java.time.LocalDate

data class UpdatePassStatusModel(
    val id: Int?,
    val groupMemberId: Int,
    val lessonId: Int,
    val date: LocalDate,
    val status: Int
)
