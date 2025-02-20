package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.GroupMemberEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberModel
import javax.inject.Inject

internal class GroupMemberEntityMapper @Inject constructor(
    private val pupilEntityMapper: PupilEntityMapper,
    private val educationPlaceEntityMapper: EducationPlaceEntityMapper
): Mapper<GroupMemberEntity, GroupMemberModel> {

    override suspend fun map(input: GroupMemberEntity): GroupMemberModel = with(input) {
        GroupMemberModel(
            id,
            firstname,
            lastname,
            patronymic,
            prefect,
            code,
            pupil?.let { pupilEntityMapper.map(it) },
            educationPlaceDto?.let { educationPlaceEntityMapper.map(it) }
        )
    }
}




















