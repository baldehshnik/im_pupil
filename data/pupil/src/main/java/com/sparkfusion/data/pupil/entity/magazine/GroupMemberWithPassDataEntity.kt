package com.sparkfusion.data.pupil.entity.magazine

import com.sparkfusion.data.pupil.entity.students.GroupMemberDataEntity

internal data class GroupMemberWithPassDataEntity(
    val getGroupMemberDto: GroupMemberDataEntity,
    val getPassDto: PassDataEntity
)
