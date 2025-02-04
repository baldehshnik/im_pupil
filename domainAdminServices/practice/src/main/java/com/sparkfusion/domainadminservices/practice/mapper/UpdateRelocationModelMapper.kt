package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.UpdateRelocationEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.UpdateRelocationModel
import javax.inject.Inject

class UpdateRelocationModelMapper @Inject constructor(
) : Mapper<UpdateRelocationModel, UpdateRelocationEntity> {

    override suspend fun map(input: UpdateRelocationModel): UpdateRelocationEntity = with(input) {
        UpdateRelocationEntity(id, name)
    }
}