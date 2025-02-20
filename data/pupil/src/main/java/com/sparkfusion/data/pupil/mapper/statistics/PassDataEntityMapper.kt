package com.sparkfusion.data.pupil.mapper.statistics

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.statistics.PassDataEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassEntity
import javax.inject.Inject

internal class PassDataEntityMapper @Inject constructor(
    private val lessonDataEntityMapper: LessonDataEntityMapper
): Mapper<PassDataEntity, PassEntity> {

    override suspend fun map(input: PassDataEntity): PassEntity = with(input) {
        PassEntity(
            id,
            date,
            lessonDataEntityMapper.map(lesson)
        )
    }
}