package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.CreateGroupMemberEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupMemberModel
import javax.inject.Inject

class CreateGroupMemberModelMapper @Inject constructor(
): Mapper<CreateGroupMemberModel, CreateGroupMemberEntity> {

    override suspend fun map(input: CreateGroupMemberModel): CreateGroupMemberEntity = with(input) {
        CreateGroupMemberEntity(firstname, lastname, patronymic, code)
    }
}