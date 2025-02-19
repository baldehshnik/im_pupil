package com.sparkfusion.data.pupil.mapper.students

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.students.GroupMemberDataEntity
import com.sparkfusion.dataport.pupil.portstudents.GroupMemberEntity
import javax.inject.Inject

internal class GroupMemberDataEntityMapper @Inject constructor(
    private val pupilDataEntityMapper: PupilDataEntityMapper,
    private val educationPlaceDataEntityMapper: EducationPlaceDataEntityMapper
) : Mapper<GroupMemberDataEntity, GroupMemberEntity> {

    override suspend fun map(input: GroupMemberDataEntity): GroupMemberEntity = with(input) {
        GroupMemberEntity(
            id,
            firstname,
            lastname,
            patronymic,
            prefect,
            code,
            pupil?.let { pupilDataEntityMapper.map(it) },
            educationPlaceDto?.let { educationPlaceDataEntityMapper.map(it) }
        )
    }
}




























