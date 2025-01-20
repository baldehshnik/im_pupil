package com.sparkfusion.domain.admin.account.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portaccount.AdminNewImageEntity
import com.sparkfusion.domain.admin.port.portaccount.AdminNewImageModel
import javax.inject.Inject

class AdminNewImageEntityMapper @Inject constructor(
): Mapper<AdminNewImageEntity, AdminNewImageModel> {

    override suspend fun map(input: AdminNewImageEntity): AdminNewImageModel {
        return AdminNewImageModel(input.icon)
    }
}
