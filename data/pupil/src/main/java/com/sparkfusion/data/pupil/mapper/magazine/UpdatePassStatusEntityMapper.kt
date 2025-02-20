package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.UpdatePassStatusDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.UpdatePassStatusEntity
import javax.inject.Inject

internal class UpdatePassStatusEntityMapper @Inject constructor(
): Mapper<UpdatePassStatusEntity, UpdatePassStatusDataEntity> {

    override suspend fun map(input: UpdatePassStatusEntity): UpdatePassStatusDataEntity = with(input) {
        UpdatePassStatusDataEntity(id, groupMemberId, lessonId, date, status)
    }
}