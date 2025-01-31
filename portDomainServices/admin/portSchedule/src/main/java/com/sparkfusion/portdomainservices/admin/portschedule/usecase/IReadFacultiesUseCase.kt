package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.model.FacultyModel
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadLessonModel
import com.sparkfusion.portdomainservices.admin.portschedule.model.ScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateScheduleModel

interface IReadFacultiesUseCase {

    suspend fun readFaculties(): Answer<List<FacultyModel>>
}

























