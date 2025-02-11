package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonWithPassStatusEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonWithPassStatusModel
import javax.inject.Inject

internal class ReadLessonWithPassStatusEntityMapper @Inject constructor(
    private val readLessonEntityMapper: ReadLessonEntityMapper,
    private val readPassEntityMapper: ReadPassEntityMapper
): Mapper<ReadLessonWithPassStatusEntity, ReadLessonWithPassStatusModel> {

    override suspend fun map(input: ReadLessonWithPassStatusEntity): ReadLessonWithPassStatusModel = with(input) {
        ReadLessonWithPassStatusModel(
            readLessonEntityMapper.map(getLessonDto),
            readPassEntityMapper.map(getPassDto)
        )
    }
}