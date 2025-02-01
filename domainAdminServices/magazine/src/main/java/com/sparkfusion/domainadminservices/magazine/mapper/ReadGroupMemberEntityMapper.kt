package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberModel
import javax.inject.Inject

class ReadGroupMemberEntityMapper @Inject constructor(
): Mapper<ReadGroupMemberEntity, ReadGroupMemberModel> {

    override suspend fun map(input: ReadGroupMemberEntity): ReadGroupMemberModel = with(input) {
        ReadGroupMemberModel(id, firstname, lastname, patronymic, isPrefect, code)
    }
}