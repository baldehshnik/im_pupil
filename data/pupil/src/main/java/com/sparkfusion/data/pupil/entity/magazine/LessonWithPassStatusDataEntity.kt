package com.sparkfusion.data.pupil.entity.magazine

import com.sparkfusion.data.pupil.entity.schedule.LessonDataEntity

internal data class LessonWithPassStatusDataEntity(
    val getLessonDto: LessonDataEntity,
    val getPassDto: PassDataEntity
)
