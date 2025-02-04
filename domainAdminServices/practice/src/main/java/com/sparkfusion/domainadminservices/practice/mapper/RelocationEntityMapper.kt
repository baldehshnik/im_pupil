package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.RelocationEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.RelocationModel
import javax.inject.Inject

class RelocationEntityMapper @Inject constructor(
): Mapper<RelocationEntity, RelocationModel> {

    override suspend fun map(input: RelocationEntity): RelocationModel = with(input) {
        RelocationModel(name)
    }
}