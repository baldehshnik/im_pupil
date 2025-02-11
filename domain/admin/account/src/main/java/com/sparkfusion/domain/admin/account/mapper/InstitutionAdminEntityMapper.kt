package com.sparkfusion.domain.admin.account.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portaccount.InstitutionAdminEntity
import com.sparkfusion.domain.admin.port.portaccount.InstitutionAdminModel
import javax.inject.Inject

internal class InstitutionAdminEntityMapper @Inject constructor(
): Mapper<InstitutionAdminEntity, InstitutionAdminModel> {

    override suspend fun map(input: InstitutionAdminEntity): InstitutionAdminModel = with(input) {
        InstitutionAdminModel(id, firstname, lastname, patronymic, email, accessMode, icon)
    }
}