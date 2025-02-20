package com.sparkfusion.data.pupil.mapper.statistics

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.students.GroupMemberDataEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.GroupMemberEntity
import javax.inject.Inject

internal class GroupMemberDataEntityMapper @Inject constructor(
    private val pupilDataEntityMapper: PupilDataEntityMapper
) : Mapper<GroupMemberDataEntity, GroupMemberEntity> {

    override suspend fun map(input: GroupMemberDataEntity): GroupMemberEntity = with(input) {
        GroupMemberEntity(
            id,
            firstname,
            lastname,
            patronymic,
            prefect,
            code,
            pupil?.let { pupilDataEntityMapper.map(it) }
        )
    }
}




















