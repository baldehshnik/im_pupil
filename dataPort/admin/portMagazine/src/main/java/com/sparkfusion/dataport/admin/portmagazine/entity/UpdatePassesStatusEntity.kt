package com.sparkfusion.dataport.admin.portmagazine.entity

import java.time.LocalDate

data class UpdatePassesStatusEntity(
    val lessonId: Int,
    val date: LocalDate,
    val updateInfos: List<UpdateInfoEntity>
)
