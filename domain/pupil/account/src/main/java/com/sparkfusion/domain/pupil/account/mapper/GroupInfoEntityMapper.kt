package com.sparkfusion.domain.pupil.account.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portaccount.entity.GroupInfoEntity
import com.sparkfusion.domain.pupil.port.portaccount.model.GroupInfoModel
import javax.inject.Inject

internal class GroupInfoEntityMapper @Inject constructor(
) : Mapper<GroupInfoEntity, GroupInfoModel> {

    override suspend fun map(input: GroupInfoEntity): GroupInfoModel = with(input) {
        GroupInfoModel(
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