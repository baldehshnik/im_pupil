package com.sparkfusion.domainadminservices.exam.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portexam.UpdateExamEntity
import com.sparkfusion.portdomainservices.admin.portexam.model.UpdateExamModel
import javax.inject.Inject

class UpdateExamModelMapper @Inject constructor(
): Mapper<UpdateExamModel, UpdateExamEntity> {

    override suspend fun map(input: UpdateExamModel): UpdateExamEntity = with(input) {
        UpdateExamEntity(id, type, name, audience, dateTime, status)
    }
}