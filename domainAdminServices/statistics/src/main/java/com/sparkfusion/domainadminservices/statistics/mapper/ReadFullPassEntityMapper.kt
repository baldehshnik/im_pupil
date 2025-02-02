package com.sparkfusion.domainadminservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassEntity
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassModel
import javax.inject.Inject

class ReadFullPassEntityMapper @Inject constructor(
    private val readLessonEntityMapper: ReadLessonEntityMapper
): Mapper<ReadFullPassEntity, ReadFullPassModel> {

    override suspend fun map(input: ReadFullPassEntity): ReadFullPassModel = with(input) {
        ReadFullPassModel(id, date, readLessonEntityMapper.map(lesson))
    }

}
