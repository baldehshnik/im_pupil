package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.GroupMemberWithPassDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.GroupMemberWithPassEntity
import javax.inject.Inject

internal class GroupMemberWithPassDataEntityMapper @Inject constructor(
    private val groupMemberDataEntityMapper: GroupMemberDataEntityMapper,
    private val passDataEntityMapper: PassDataEntityMapper
) : Mapper<GroupMemberWithPassDataEntity, GroupMemberWithPassEntity> {

    override suspend fun map(input: GroupMemberWithPassDataEntity): GroupMemberWithPassEntity =
        with(input) {
            GroupMemberWithPassEntity(
                groupMemberDataEntityMapper.map(getGroupMemberDto),
                passDataEntityMapper.map(getPassDto)
            )
        }
}




















