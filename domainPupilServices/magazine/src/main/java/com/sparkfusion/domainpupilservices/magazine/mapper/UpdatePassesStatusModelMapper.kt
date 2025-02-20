package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.UpdatePassesStatusEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassesStatusModel
import javax.inject.Inject

internal class UpdatePassesStatusModelMapper @Inject constructor(
    private val updateInfoModelMapper: UpdateInfoModelMapper
): Mapper<UpdatePassesStatusModel, UpdatePassesStatusEntity> {

    override suspend fun map(input: UpdatePassesStatusModel): UpdatePassesStatusEntity = with(input) {
        UpdatePassesStatusEntity(
            lessonId,
            date,
            updateInfos.map { updateInfoModelMapper.map(it) }
        )
    }
}