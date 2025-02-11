package com.sparkfusion.domain.pupil.port.portauth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.portauth.model.EducationalInstitutionModel

interface IReadInstitutionByNamePartUseCase {

    suspend fun readInstitutionByNamePart(namePart: String): Answer<List<EducationalInstitutionModel>>
}