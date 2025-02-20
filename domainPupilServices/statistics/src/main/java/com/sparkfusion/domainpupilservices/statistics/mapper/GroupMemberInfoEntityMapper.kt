package com.sparkfusion.domainpupilservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portstatistics.entity.GroupMemberInfoEntity
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberInfoModel
import javax.inject.Inject

internal class GroupMemberInfoEntityMapper @Inject constructor(
) : Mapper<GroupMemberInfoEntity, GroupMemberInfoModel> {

    override suspend fun map(input: GroupMemberInfoEntity): GroupMemberInfoModel = with(input) {
        GroupMemberInfoModel(id, firstname, lastname, patronymic, isPrefect, code)
    }
}