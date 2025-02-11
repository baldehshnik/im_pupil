package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.UpdateInformationBlockEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.UpdateInformationBlockModel
import javax.inject.Inject

internal class UpdateInformationBlockModelMapper @Inject constructor(
): Mapper<UpdateInformationBlockModel, UpdateInformationBlockEntity> {

    override suspend fun map(input: UpdateInformationBlockModel): UpdateInformationBlockEntity = with(input) {
        UpdateInformationBlockEntity(id, title, content)
    }
}