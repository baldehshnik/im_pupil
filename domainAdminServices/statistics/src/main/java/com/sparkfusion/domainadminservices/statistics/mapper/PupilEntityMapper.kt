package com.sparkfusion.domainadminservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstatistics.entity.PupilEntity
import com.sparkfusion.portdomainservices.admin.portstatistics.model.PupilModel
import javax.inject.Inject

class PupilEntityMapper @Inject constructor(
): Mapper<PupilEntity, PupilModel> {

    override suspend fun map(input: PupilEntity): PupilModel = with(input) {
        PupilModel(id, icon, code, status)
    }
}