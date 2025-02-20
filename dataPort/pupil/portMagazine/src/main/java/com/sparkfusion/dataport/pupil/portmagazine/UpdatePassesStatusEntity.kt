package com.sparkfusion.dataport.pupil.portmagazine

import java.time.LocalDate

data class UpdatePassesStatusEntity(
    val lessonId: Int,
    val date: LocalDate,
    val updateInfos: List<UpdateInfoEntity>
)
