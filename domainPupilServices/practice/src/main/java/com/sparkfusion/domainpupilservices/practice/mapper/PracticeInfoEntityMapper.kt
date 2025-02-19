package com.sparkfusion.domainpupilservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portpractice.PracticeInfoEntity
import com.sparkfusion.portdomainservices.pupil.portpractice.PracticeInfoModel
import javax.inject.Inject

internal class PracticeInfoEntityMapper @Inject constructor(
): Mapper<PracticeInfoEntity, PracticeInfoModel> {

    override suspend fun map(input: PracticeInfoEntity): PracticeInfoModel = with(input) {
        PracticeInfoModel(id, icon, payAbility, description, workType, title, website)
    }
}