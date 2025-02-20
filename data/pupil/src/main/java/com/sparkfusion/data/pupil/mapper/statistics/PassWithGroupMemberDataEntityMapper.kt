package com.sparkfusion.data.pupil.mapper.statistics

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.statistics.PassWithGroupMemberDataEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassWithGroupMemberEntity
import javax.inject.Inject

internal class PassWithGroupMemberDataEntityMapper @Inject constructor(
    private val lessonDataEntityMapper: LessonDataEntityMapper,
    private val groupMemberInfoDataEntityMapper: GroupMemberInfoDataEntityMapper
): Mapper<PassWithGroupMemberDataEntity, PassWithGroupMemberEntity> {

    override suspend fun map(input: PassWithGroupMemberDataEntity): PassWithGroupMemberEntity = with(input) {
        PassWithGroupMemberEntity(
            id,
            date,
            lessonDataEntityMapper.map(lesson),
            groupMemberInfoDataEntityMapper.map(groupMember)
        )
    }
}






















