package com.sparkfusion.domainadminservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassWithGroupMemberEntity
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassWithGroupMemberModel
import javax.inject.Inject

internal class ReadFullPassWithGroupMemberEntityMapper @Inject constructor(
    private val lessonEntityMapper: ReadLessonEntityMapper,
    private val readGroupMemberPersonalInfoEntityMapper: ReadGroupMemberPersonalInfoEntityMapper
) : Mapper<ReadFullPassWithGroupMemberEntity, ReadFullPassWithGroupMemberModel> {

    override suspend fun map(input: ReadFullPassWithGroupMemberEntity): ReadFullPassWithGroupMemberModel =
        with(input) {
            ReadFullPassWithGroupMemberModel(
                id,
                date,
                lessonEntityMapper.map(lesson),
                readGroupMemberPersonalInfoEntityMapper.map(groupMember)
            )
        }
}