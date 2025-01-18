package com.sparkfusion.domain.admin.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portAuth.entity.EducationalInstitutionEntity
import com.sparkfusion.domain.admin.port.portauth.InstitutionModel
import javax.inject.Inject

class EducationInstitutionEntityMapper @Inject constructor(
) : Mapper<EducationalInstitutionEntity, InstitutionModel> {

    override suspend fun map(input: EducationalInstitutionEntity): InstitutionModel = with(input) {
        InstitutionModel(name, abbreviation, type)
    }
}


