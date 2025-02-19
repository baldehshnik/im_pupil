package com.sparkfusion.data.pupil.mapper.students

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.students.PupilDataEntity
import com.sparkfusion.dataport.pupil.portstudents.PupilEntity
import javax.inject.Inject

internal class PupilDataEntityMapper @Inject constructor(
) : Mapper<PupilDataEntity, PupilEntity> {

    override suspend fun map(input: PupilDataEntity): PupilEntity = with(input) {
        PupilEntity(id, icon, code, status)
    }
}