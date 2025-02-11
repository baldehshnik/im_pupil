package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.RelocationEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.RelocationModel
import javax.inject.Inject

internal class RelocationModelMapper @Inject constructor(
): Mapper<RelocationModel, RelocationEntity> {

    override suspend fun map(input: RelocationModel): RelocationEntity = with(input) {
        RelocationEntity(name)
    }
}