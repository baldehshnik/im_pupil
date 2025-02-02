package com.sparkfusion.domainadminservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadGroupMemberPersonalInfoEntity
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadGroupMemberPersonalInfoModel
import javax.inject.Inject

class ReadGroupMemberPersonalInfoEntityMapper @Inject constructor(
): Mapper<ReadGroupMemberPersonalInfoEntity, ReadGroupMemberPersonalInfoModel> {

    override suspend fun map(input: ReadGroupMemberPersonalInfoEntity): ReadGroupMemberPersonalInfoModel = with(input) {
        ReadGroupMemberPersonalInfoModel(id, firstname, lastname, patronymic, isPrefect, code)
    }
}