package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.UpdateInfoEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdateInfoModel
import javax.inject.Inject

internal class UpdateInfoModelMapper @Inject constructor(
): Mapper<UpdateInfoModel, UpdateInfoEntity> {

    override suspend fun map(input: UpdateInfoModel): UpdateInfoEntity = with(input) {
        UpdateInfoEntity(id, groupMemberId, status)
    }
}