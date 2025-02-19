package com.sparkfusion.portdomainservices.pupil.portsection

import com.sparkfusion.core.common.result.Answer

interface IReadSectionsUseCase {

    suspend fun readSections(): Answer<List<SectionModel>>
}