package com.sparkfusion.dataport.pupil.portsection

import com.sparkfusion.core.common.result.Answer

interface ISectionRepository {

    suspend fun readSections(): Answer<List<SectionEntity>>

    suspend fun readSectionById(id: Int): Answer<SectionEntity>
}