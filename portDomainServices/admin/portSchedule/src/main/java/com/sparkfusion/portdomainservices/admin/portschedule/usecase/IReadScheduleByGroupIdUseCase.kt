package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.ScheduleModel

interface IReadScheduleByGroupIdUseCase {

    suspend fun readScheduleByGroupId(groupId: Int): Answer<List<ScheduleModel>>
}