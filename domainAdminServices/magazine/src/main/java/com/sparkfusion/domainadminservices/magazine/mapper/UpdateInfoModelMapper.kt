package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdateInfoEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdateInfoModel
import javax.inject.Inject

class UpdateInfoModelMapper @Inject constructor(
) : Mapper<UpdateInfoModel, UpdateInfoEntity> {

    override suspend fun map(input: UpdateInfoModel): UpdateInfoEntity = with(input) {
        UpdateInfoEntity(id, groupMemberId, status)
    }
}