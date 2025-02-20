package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.UpdateInfoDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.UpdateInfoEntity
import javax.inject.Inject

internal class UpdateInfoEntityMapper @Inject constructor(
): Mapper<UpdateInfoEntity, UpdateInfoDataEntity> {

    override suspend fun map(input: UpdateInfoEntity): UpdateInfoDataEntity = with(input) {
        UpdateInfoDataEntity(id, groupMemberId, status)
    }
}