package com.sparkfusion.domain.admin.confirmation.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portconfirmation.AdminEntity
import com.sparkfusion.domain.admin.port.portconfirmation.AdminModel
import javax.inject.Inject

internal class AdminEntityMapper @Inject constructor(
) : Mapper<AdminEntity, AdminModel> {

    override suspend fun map(input: AdminEntity): AdminModel = with(input) {
        AdminModel(id, firstname, lastname, patronymic, email, accessMode, icon)
    }
}