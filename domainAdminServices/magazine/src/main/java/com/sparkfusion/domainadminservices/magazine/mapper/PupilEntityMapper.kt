package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonPupilDataEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.PupilModel
import javax.inject.Inject

internal class PupilEntityMapper @Inject constructor(
): Mapper<CommonPupilDataEntity, PupilModel> {

    override suspend fun map(input: CommonPupilDataEntity): PupilModel = with(input) {
        PupilModel(id, icon, code, status)
    }
}