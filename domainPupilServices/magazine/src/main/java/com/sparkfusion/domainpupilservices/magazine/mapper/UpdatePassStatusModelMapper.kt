package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.UpdatePassStatusEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassStatusModel
import javax.inject.Inject

internal class UpdatePassStatusModelMapper @Inject constructor(
): Mapper<UpdatePassStatusModel, UpdatePassStatusEntity> {

    override suspend fun map(input: UpdatePassStatusModel): UpdatePassStatusEntity = with(input) {
        UpdatePassStatusEntity(id, groupMemberId, lessonId, date, status)
    }
}