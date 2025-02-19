package com.sparkfusion.data.pupil.mapper.practice

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.practice.PracticeListDataEntity
import com.sparkfusion.dataport.pupil.portpractice.PracticeListEntity
import javax.inject.Inject

internal class PracticeListDataEntityMapper @Inject constructor(
) : Mapper<PracticeListDataEntity, PracticeListEntity> {

    override suspend fun map(input: PracticeListDataEntity): PracticeListEntity = with(input) {
        PracticeListEntity(id, icon, payAbility, description, title)
    }
}


















