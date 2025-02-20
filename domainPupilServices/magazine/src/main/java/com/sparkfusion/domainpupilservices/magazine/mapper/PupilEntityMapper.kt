package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.PupilEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.PupilModel
import javax.inject.Inject

internal class PupilEntityMapper @Inject constructor(
): Mapper<PupilEntity, PupilModel> {

    override suspend fun map(input: PupilEntity): PupilModel = with(input) {
        PupilModel(id, icon, code, status)
    }
}