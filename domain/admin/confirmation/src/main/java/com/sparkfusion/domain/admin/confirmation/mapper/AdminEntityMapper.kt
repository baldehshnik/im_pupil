package com.sparkfusion.domain.admin.confirmation.mapper

import android.util.Log
import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portconfirmation.AdminEntity
import com.sparkfusion.domain.admin.port.portconfirmation.AdminModel
import javax.inject.Inject

class AdminEntityMapper @Inject constructor(
): Mapper<AdminEntity, AdminModel> {

    override suspend fun map(input: AdminEntity): AdminModel = with(input) {
        Log.d("TAGTAG", "MODEL - $input")
        AdminModel(id, firstname, lastname, patronymic, email, accessMode, icon)
    }
}