package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.CreateGroupEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupModel
import javax.inject.Inject

class CreateGroupModelMapper @Inject constructor(
    private val createGroupMemberModelMapper: CreateGroupMemberModelMapper
) : Mapper<CreateGroupModel, CreateGroupEntity> {

    override suspend fun map(input: CreateGroupModel): CreateGroupEntity = with(input) {
        CreateGroupEntity(
            specialityId,
            name,
            course,
            groupMemberDtos.map { createGroupMemberModelMapper.map(it) })
    }
}