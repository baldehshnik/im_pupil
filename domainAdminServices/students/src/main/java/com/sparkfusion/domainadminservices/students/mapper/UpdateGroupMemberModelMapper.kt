package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.UpdateGroupMemberEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupMemberModel
import javax.inject.Inject

internal class UpdateGroupMemberModelMapper @Inject constructor(
): Mapper<UpdateGroupMemberModel, UpdateGroupMemberEntity> {

    override suspend fun map(input: UpdateGroupMemberModel): UpdateGroupMemberEntity = with(input) {
        UpdateGroupMemberEntity(id, firstname, lastname, patronymic, code)
    }
}