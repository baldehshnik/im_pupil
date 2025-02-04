package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.InformationBlockEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.InformationBlockModel
import javax.inject.Inject

class InformationBlockModelMapper @Inject constructor(
): Mapper<InformationBlockModel, InformationBlockEntity> {

    override suspend fun map(input: InformationBlockModel): InformationBlockEntity = with(input) {
        InformationBlockEntity(title, content)
    }
}




















