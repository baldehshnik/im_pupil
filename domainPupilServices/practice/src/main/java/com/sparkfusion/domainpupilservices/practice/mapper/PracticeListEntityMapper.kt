package com.sparkfusion.domainpupilservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portpractice.PracticeListEntity
import com.sparkfusion.portdomainservices.pupil.portpractice.PracticeListModel
import javax.inject.Inject

internal class PracticeListEntityMapper @Inject constructor(
): Mapper<PracticeListEntity, PracticeListModel> {

    override suspend fun map(input: PracticeListEntity): PracticeListModel = with(input) {
        PracticeListModel(id, icon, payAbility, description, title)
    }
}