package com.sparkfusion.domain.pupil.account.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portaccount.entity.PupilEntity
import com.sparkfusion.domain.pupil.port.portaccount.model.PupilModel
import javax.inject.Inject

internal class PupilEntityMapper @Inject constructor(
): Mapper<PupilEntity, PupilModel> {

    override suspend fun map(input: PupilEntity): PupilModel = with(input) {
        PupilModel(id, icon, code, status)
    }
}