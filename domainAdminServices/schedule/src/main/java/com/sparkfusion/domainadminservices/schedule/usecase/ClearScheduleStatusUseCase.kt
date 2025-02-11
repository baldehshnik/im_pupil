package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IClearScheduleStatusUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ClearScheduleStatusUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository
): IClearScheduleStatusUseCase {

    override suspend fun clearScheduleStatus(scheduleId: Int): Answer<Unit> {
        return scheduleRepository.clearScheduleStatus(scheduleId)
    }
}