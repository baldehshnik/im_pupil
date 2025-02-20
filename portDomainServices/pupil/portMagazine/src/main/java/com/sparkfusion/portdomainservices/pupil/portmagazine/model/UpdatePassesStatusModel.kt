package com.sparkfusion.portdomainservices.pupil.portmagazine.model

import java.time.LocalDate

data class UpdatePassesStatusModel(
    val lessonId: Int,
    val date: LocalDate,
    val updateInfos: List<UpdateInfoModel>
)
