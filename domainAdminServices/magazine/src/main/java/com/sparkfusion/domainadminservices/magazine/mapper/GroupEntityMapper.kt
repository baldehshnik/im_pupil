package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.GroupModel
import javax.inject.Inject

internal class GroupEntityMapper @Inject constructor(
): Mapper<CommonGroupDataEntity, GroupModel> {

    override suspend fun map(input: CommonGroupDataEntity): GroupModel = with(input) {
        GroupModel(id, name, course)
    }
}