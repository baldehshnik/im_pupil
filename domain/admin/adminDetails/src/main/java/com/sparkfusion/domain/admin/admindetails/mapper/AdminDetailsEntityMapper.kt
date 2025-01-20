package com.sparkfusion.domain.admin.admindetails.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portadmindetails.AdminDetailsEntity
import com.sparkfusion.domain.admin.port.portadmindetails.AdminDetailsModel
import javax.inject.Inject

class AdminDetailsEntityMapper @Inject constructor(
): Mapper<AdminDetailsEntity, AdminDetailsModel> {

    override suspend fun map(input: AdminDetailsEntity): AdminDetailsModel = with(input) {
        AdminDetailsModel(id, firstname, lastname, patronymic, email, accessMode, icon)
    }
}