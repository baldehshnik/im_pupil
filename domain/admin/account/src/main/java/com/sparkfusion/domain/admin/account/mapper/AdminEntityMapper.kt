package com.sparkfusion.domain.admin.account.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portaccount.AdminEntity
import com.sparkfusion.domain.admin.port.portaccount.AdminAccountModel
import javax.inject.Inject

class AdminEntityMapper @Inject constructor(
) : Mapper<AdminEntity, AdminAccountModel> {

    override suspend fun map(input: AdminEntity): AdminAccountModel = with(input) {
        AdminAccountModel(firstname, lastname, patronymic, accessMode, icon)
    }
}
