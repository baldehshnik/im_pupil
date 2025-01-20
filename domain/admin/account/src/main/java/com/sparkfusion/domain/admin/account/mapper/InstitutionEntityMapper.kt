package com.sparkfusion.domain.admin.account.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portinstitution.InstitutionEntity
import com.sparkfusion.domain.admin.port.portaccount.InstitutionModel
import javax.inject.Inject

class InstitutionEntityMapper @Inject constructor(
) : Mapper<InstitutionEntity, InstitutionModel> {

    override suspend fun map(input: InstitutionEntity): InstitutionModel = with(input) {
        InstitutionModel(id, name, abbreviation, type, address, phone)
    }
}















