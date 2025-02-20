package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.GroupMemberWithPassEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel
import javax.inject.Inject

internal class GroupMemberWithPassEntityMapper @Inject constructor(
    private val groupMemberEntityMapper: GroupMemberEntityMapper,
    private val passEntityMapper: PassEntityMapper
): Mapper<GroupMemberWithPassEntity, GroupMemberWithPassModel> {

    override suspend fun map(input: GroupMemberWithPassEntity): GroupMemberWithPassModel = with(input) {
        GroupMemberWithPassModel(
            groupMemberEntityMapper.map(getGroupMemberDto),
            passEntityMapper.map(getPassDto)
        )
    }
}
























