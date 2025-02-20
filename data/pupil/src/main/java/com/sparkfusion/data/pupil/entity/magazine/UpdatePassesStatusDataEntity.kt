package com.sparkfusion.data.pupil.entity.magazine

import java.time.LocalDate

internal data class UpdatePassesStatusDataEntity(
    val lessonId: Int,
    val date: LocalDate,
    val updateInfos: List<UpdateInfoDataEntity>
)
