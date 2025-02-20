package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.LessonWithPassStatusDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.LessonWithPassStatusEntity
import javax.inject.Inject

internal class LessonWithPassStatusDataEntityMapper @Inject constructor(
    private val lessonDataEntityMapper: LessonDataEntityMapper,
    private val passDataEntityMapper: PassDataEntityMapper
): Mapper<LessonWithPassStatusDataEntity, LessonWithPassStatusEntity> {

    override suspend fun map(input: LessonWithPassStatusDataEntity): LessonWithPassStatusEntity = with(input) {
        LessonWithPassStatusEntity(
            lessonDataEntityMapper.map(getLessonDto),
            passDataEntityMapper.map(getPassDto)
        )
    }
}