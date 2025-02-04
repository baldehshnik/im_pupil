package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.InformationBlockEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.InformationBlockModel
import javax.inject.Inject

class InformationBlockEntityMapper @Inject constructor(
): Mapper<InformationBlockEntity, InformationBlockModel> {

    override suspend fun map(input: InformationBlockEntity): InformationBlockModel = with(input) {
        InformationBlockModel(title, content)
    }
}