package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.FacultyModel

interface IReadFacultiesUseCase {

    suspend fun readFaculties(): Answer<List<FacultyModel>>
}