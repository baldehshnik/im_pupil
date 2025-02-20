package com.sparkfusion.domainpupilservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassEntity
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassModel
import javax.inject.Inject

internal class PassEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper
): Mapper<PassEntity, PassModel> {

    override suspend fun map(input: PassEntity): PassModel = with(input) {
        PassModel(
            id,
            date,
            lessonEntityMapper.map(lesson)
        )
    }
}