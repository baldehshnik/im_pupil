package com.sparkfusion.domain.admin.home.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portaccount.AdminEntity
import com.sparkfusion.domain.admin.port.porthome.AccountModel
import javax.inject.Inject

internal class AdminAccountEntityMapper @Inject constructor(
) : Mapper<AdminEntity, AccountModel> {

    override suspend fun map(input: AdminEntity): AccountModel {
        return AccountModel(input.firstname)
    }
}