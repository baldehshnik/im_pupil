package com.sparkfusion.dataport.pupil.porthome

import com.sparkfusion.core.common.result.Answer

interface IHomeRepository {

    suspend fun readEvents(): Answer<List<ReadInstitutionEventEntity>>
}



























