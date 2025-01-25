package com.sparkfusion.domain.admin.confirmation.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portconfirmation.PupilEntity
import com.sparkfusion.domain.admin.port.portconfirmation.PupilModel
import javax.inject.Inject

class PupilEntityMapper @Inject constructor(
): Mapper<PupilEntity, PupilModel> {

    override suspend fun map(input: PupilEntity): PupilModel = with(input) {
        PupilModel(id, code, status)
    }
}