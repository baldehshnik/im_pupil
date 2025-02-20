package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.UpdatePassesStatusDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.UpdatePassesStatusEntity
import javax.inject.Inject

internal class UpdatePassesStatusEntityMapper @Inject constructor(
    private val updateInfoEntityMapper: UpdateInfoEntityMapper
) : Mapper<UpdatePassesStatusEntity, UpdatePassesStatusDataEntity> {

    override suspend fun map(input: UpdatePassesStatusEntity): UpdatePassesStatusDataEntity =
        with(input) {
            UpdatePassesStatusDataEntity(
                lessonId,
                date,
                updateInfos.map { updateInfoEntityMapper.map(it) }
            )
        }
}




















