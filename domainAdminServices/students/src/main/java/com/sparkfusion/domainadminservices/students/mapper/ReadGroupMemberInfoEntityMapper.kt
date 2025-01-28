package com.sparkfusion.domainadminservices.students.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupMemberInfoEntity
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberInfoModel
import javax.inject.Inject

class ReadGroupMemberInfoEntityMapper @Inject constructor(
) : Mapper<ReadGroupMemberInfoEntity, ReadGroupMemberInfoModel> {

    override suspend fun map(input: ReadGroupMemberInfoEntity): ReadGroupMemberInfoModel =
        with(input) {
            ReadGroupMemberInfoModel(id, firstname, lastname, patronymic, prefect, code)
        }
}