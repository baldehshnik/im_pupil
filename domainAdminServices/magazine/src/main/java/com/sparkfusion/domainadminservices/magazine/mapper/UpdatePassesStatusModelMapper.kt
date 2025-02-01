package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassesStatusEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassesStatusModel
import javax.inject.Inject

class UpdatePassesStatusModelMapper @Inject constructor(
    private val updateInfoModelMapper: UpdateInfoModelMapper
) : Mapper<UpdatePassesStatusModel, UpdatePassesStatusEntity> {

    override suspend fun map(input: UpdatePassesStatusModel): UpdatePassesStatusEntity =
        with(input) {
            UpdatePassesStatusEntity(
                lessonId,
                date,
                updateInfos.map { updateInfoModelMapper.map(it) }
            )
        }
}