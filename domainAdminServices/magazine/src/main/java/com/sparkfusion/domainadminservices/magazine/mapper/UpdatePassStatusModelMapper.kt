package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassStatusEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassStatusModel
import javax.inject.Inject

internal class UpdatePassStatusModelMapper @Inject constructor(
): Mapper<UpdatePassStatusModel, UpdatePassStatusEntity> {

    override suspend fun map(input: UpdatePassStatusModel): UpdatePassStatusEntity = with(input) {
        UpdatePassStatusEntity(id, groupMemberId, lessonId, date, status)
    }
}