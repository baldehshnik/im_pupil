package com.sparkfusion.dataport.pupil.portpractice

import com.sparkfusion.core.common.result.Answer

interface IPracticeRepository {

    suspend fun readPractices(): Answer<List<PracticeListEntity>>

    suspend fun readPracticeById(id: Int): Answer<PracticeInfoEntity>
}

























