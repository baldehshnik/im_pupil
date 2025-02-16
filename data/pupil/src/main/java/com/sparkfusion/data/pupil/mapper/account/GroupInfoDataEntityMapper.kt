package com.sparkfusion.data.pupil.mapper.account

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.account.GroupInfoDataEntity
import com.sparkfusion.dataport.pupil.portaccount.entity.GroupInfoEntity
import javax.inject.Inject

internal class GroupInfoDataEntityMapper @Inject constructor(
) : Mapper<GroupInfoDataEntity, GroupInfoEntity> {

    override suspend fun map(input: GroupInfoDataEntity): GroupInfoEntity = with(input) {
        GroupInfoEntity(
            institutionAbbreviation,
            institutionName,
            institutionAddress,
            institutionPhone,
            specialityName,
            groupName,
            groupMembersCount
        )
    }
}






















