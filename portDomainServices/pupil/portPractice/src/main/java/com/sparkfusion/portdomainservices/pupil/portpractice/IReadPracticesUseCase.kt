package com.sparkfusion.portdomainservices.pupil.portpractice

import com.sparkfusion.core.common.result.Answer

interface IReadPracticesUseCase {

    suspend fun readPractices(): Answer<List<PracticeListModel>>
}
























