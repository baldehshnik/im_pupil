package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupWithMembersEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupWithMembersModel
import javax.inject.Inject

internal class ReadGroupWithMembersEntityMapper @Inject constructor(
    private val readGroupMemberInfoEntityMapper: ReadGroupMemberInfoEntityMapper
): Mapper<ReadGroupWithMembersEntity, ReadGroupWithMembersModel> {

    override suspend fun map(input: ReadGroupWithMembersEntity): ReadGroupWithMembersModel = with(input) {
        ReadGroupWithMembersModel(
            id,
            name,
            course,
            members.map { model -> readGroupMemberInfoEntityMapper.map(model) }
        )
    }
}
















