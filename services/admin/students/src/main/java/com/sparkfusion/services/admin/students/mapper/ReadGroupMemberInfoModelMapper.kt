package com.sparkfusion.services.admin.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberInfoModel
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupMemberModel
import javax.inject.Inject

class ReadGroupMemberInfoModelMapper @Inject constructor(
): Mapper<ReadGroupMemberInfoModel, UpdateGroupMemberModel> {

    override suspend fun map(input: ReadGroupMemberInfoModel): UpdateGroupMemberModel = with(input) {
        UpdateGroupMemberModel(id, firstname, lastname, patronymic, code)
    }
}
















