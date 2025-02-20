package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.PassDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.PassEntity
import javax.inject.Inject

internal class PassDataEntityMapper @Inject constructor(
) : Mapper<PassDataEntity, PassEntity> {

    override suspend fun map(input: PassDataEntity): PassEntity = with(input) {
        PassEntity(
            id,
            date,
            status
        )
    }
}