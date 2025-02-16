package com.sparkfusion.domain.pupil.port.porthome.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.porthome.model.ReadInstitutionEventModel

interface IReadEventsUseCase {

    suspend fun readEvents(): Answer<List<ReadInstitutionEventModel>>
}