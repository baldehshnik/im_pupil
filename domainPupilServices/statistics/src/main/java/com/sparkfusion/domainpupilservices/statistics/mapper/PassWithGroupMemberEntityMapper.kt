package com.sparkfusion.domainpupilservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassWithGroupMemberEntity
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassWithGroupMemberModel
import javax.inject.Inject

internal class PassWithGroupMemberEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper,
    private val groupMemberInfoEntityMapper: GroupMemberInfoEntityMapper
) : Mapper<PassWithGroupMemberEntity, PassWithGroupMemberModel> {

    override suspend fun map(input: PassWithGroupMemberEntity): PassWithGroupMemberModel =
        with(input) {
            PassWithGroupMemberModel(
                id,
                date,
                lessonEntityMapper.map(lesson),
                groupMemberInfoEntityMapper.map(groupMember)
            )
        }
}





















