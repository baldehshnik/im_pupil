package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.UpdateGroupEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupModel
import javax.inject.Inject

internal class UpdateGroupModelMapper @Inject constructor(
    private val updateGroupMemberModelMapper: UpdateGroupMemberModelMapper
) : Mapper<UpdateGroupModel, UpdateGroupEntity> {

    override suspend fun map(input: UpdateGroupModel): UpdateGroupEntity = with(input) {
        UpdateGroupEntity(id, course, groupMemberDtos.map { updateGroupMemberModelMapper.map(it) })
    }
}