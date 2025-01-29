package com.sparkfusion.domainadminservices.exam.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portexam.AddExamEntity
import com.sparkfusion.portdomainservices.admin.portexam.model.AddExamModel
import javax.inject.Inject

class AddExamModelMapper @Inject constructor(
): Mapper<AddExamModel, AddExamEntity> {

    override suspend fun map(input: AddExamModel): AddExamEntity = with(input) {
        AddExamEntity(groupId, type, name, audience, dateTime)
    }
}