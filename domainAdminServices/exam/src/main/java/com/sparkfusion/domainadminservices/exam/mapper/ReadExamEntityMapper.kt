package com.sparkfusion.domainadminservices.exam.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.exam.CommonExamDataEntity
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel
import javax.inject.Inject

internal class ReadExamEntityMapper @Inject constructor(
): Mapper<CommonExamDataEntity, ReadExamModel> {

    override suspend fun map(input: CommonExamDataEntity): ReadExamModel = with(input) {
        ReadExamModel(id, type, name, audience, dateTime, status)
    }
}