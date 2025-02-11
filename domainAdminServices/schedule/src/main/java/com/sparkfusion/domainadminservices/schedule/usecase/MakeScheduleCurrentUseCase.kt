package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IMakeScheduleCurrentUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class MakeScheduleCurrentUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository
): IMakeScheduleCurrentUseCase {

    override suspend fun makeScheduleAsACurrent(scheduleId: Int): Answer<Unit> {
        return scheduleRepository.makeScheduleAsACurrent(scheduleId)
    }
}