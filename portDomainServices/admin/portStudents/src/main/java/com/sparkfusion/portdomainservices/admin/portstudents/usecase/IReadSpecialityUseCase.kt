package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.SpecialityModel

interface IReadSpecialityUseCase {

    suspend fun readSpecialitiesByFaculty(facultyId: Int): Answer<List<SpecialityModel>>
}