package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupMemberEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel
import javax.inject.Inject

class ReadGroupMemberEntityMapper @Inject constructor(
    private val pupilEntityMapper: PupilEntityMapper,
    private val educationPlaceEntityMapper: EducationPlaceEntityMapper
) : Mapper<ReadGroupMemberEntity, ReadGroupMemberModel> {

    override suspend fun map(input: ReadGroupMemberEntity): ReadGroupMemberModel = with(input) {
        ReadGroupMemberModel(
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