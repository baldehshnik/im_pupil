package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.LessonWithPassStatusEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonWithPassStatusModel
import javax.inject.Inject

internal class LessonWithPassStatusEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper,
    private val passEntityMapper: PassEntityMapper
): Mapper<LessonWithPassStatusEntity, LessonWithPassStatusModel> {

    override suspend fun map(input: LessonWithPassStatusEntity): LessonWithPassStatusModel = with(input) {
        LessonWithPassStatusModel(
            lessonEntityMapper.map(getLessonDto),
            passEntityMapper.map(getPassDto)
        )
    }
}