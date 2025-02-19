package com.sparkfusion.portdomainservices.pupil.portsection

import com.sparkfusion.core.common.result.Answer

interface IReadSectionByIdUseCase {

    suspend fun readSectionById(id: Int): Answer<SectionModel>
}