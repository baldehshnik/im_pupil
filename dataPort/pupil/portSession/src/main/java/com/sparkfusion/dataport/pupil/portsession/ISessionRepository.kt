package com.sparkfusion.dataport.pupil.portsession

import com.sparkfusion.core.common.result.Answer
import java.time.LocalDate

interface ISessionRepository {

    suspend fun readExams(date: LocalDate): Answer<List<SessionEntity>>
}



























