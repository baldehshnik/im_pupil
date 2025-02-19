package com.sparkfusion.data.pupil.mapper.practice

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.practice.PracticeInfoDataEntity
import com.sparkfusion.dataport.pupil.portpractice.PracticeInfoEntity
import javax.inject.Inject

internal class PracticeInfoDataEntityMapper @Inject constructor(
): Mapper<PracticeInfoDataEntity, PracticeInfoEntity> {

    override suspend fun map(input: PracticeInfoDataEntity): PracticeInfoEntity = with(input) {
        PracticeInfoEntity(id, icon, payAbility, description, workType, title, website)
    }
}