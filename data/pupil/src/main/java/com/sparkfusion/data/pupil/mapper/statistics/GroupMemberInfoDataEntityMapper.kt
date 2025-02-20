package com.sparkfusion.data.pupil.mapper.statistics

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.statistics.GroupMemberInfoDataEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.GroupMemberInfoEntity
import javax.inject.Inject

internal class GroupMemberInfoDataEntityMapper @Inject constructor(
): Mapper<GroupMemberInfoDataEntity, GroupMemberInfoEntity> {

    override suspend fun map(input: GroupMemberInfoDataEntity): GroupMemberInfoEntity = with(input) {
        GroupMemberInfoEntity(id, firstname, lastname, patronymic, prefect, code)
    }
}