package com.sparkfusion.portdomainservices.pupil.portpractice

import com.sparkfusion.core.common.result.Answer

interface IReadPracticeByIdUseCase {

    suspend fun readPracticeById(id: Int): Answer<PracticeInfoModel>
}