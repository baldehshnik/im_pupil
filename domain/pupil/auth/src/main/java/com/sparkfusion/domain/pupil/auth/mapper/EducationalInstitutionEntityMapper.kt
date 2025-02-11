package com.sparkfusion.domain.pupil.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portauth.entity.EducationalInstitutionEntity
import com.sparkfusion.domain.pupil.port.portauth.model.EducationalInstitutionModel
import javax.inject.Inject

internal class EducationalInstitutionEntityMapper @Inject constructor(
): Mapper<EducationalInstitutionEntity, EducationalInstitutionModel> {

    override suspend fun map(input: EducationalInstitutionEntity): EducationalInstitutionModel = with(input) {
        EducationalInstitutionModel(id, name, abbreviation, type)
    }
}