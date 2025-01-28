package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.GroupEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.GroupModel
import javax.inject.Inject

class GroupEntityMapper @Inject constructor(
) : Mapper<GroupEntity, GroupModel> {

    override suspend fun map(input: GroupEntity): GroupModel = with(input) {
        GroupModel(id, name, course)
    }
}