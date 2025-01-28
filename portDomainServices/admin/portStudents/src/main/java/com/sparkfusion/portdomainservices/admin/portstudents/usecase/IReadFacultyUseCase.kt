package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.FacultyModel

interface IReadFacultyUseCase {

    suspend fun readFaculties(): Answer<List<FacultyModel>>
}