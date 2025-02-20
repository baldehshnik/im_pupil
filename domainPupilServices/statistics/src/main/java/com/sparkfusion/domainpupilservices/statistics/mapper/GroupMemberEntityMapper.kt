package com.sparkfusion.domainpupilservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portstatistics.entity.GroupMemberEntity
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberModel
import javax.inject.Inject

internal class GroupMemberEntityMapper @Inject constructor(
    private val pupilEntityMapper: PupilEntityMapper
) : Mapper<GroupMemberEntity, GroupMemberModel> {

    override suspend fun map(input: GroupMemberEntity): GroupMemberModel = with(input) {
        GroupMemberModel(
            id,
            firstname,
            lastname,
            patronymic,
            isPrefect,
            code,
            pupil?.let { pupilEntityMapper.map(it) }
        )
    }
}