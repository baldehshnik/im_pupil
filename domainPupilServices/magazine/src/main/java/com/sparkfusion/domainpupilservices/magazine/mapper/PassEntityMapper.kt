package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.PassEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.PassModel
import javax.inject.Inject

internal class PassEntityMapper @Inject constructor(
) : Mapper<PassEntity, PassModel> {

    override suspend fun map(input: PassEntity): PassModel = with(input) {
        PassModel(
            id,
            date,
            status
        )
    }
}