package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.ReadListPracticeEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadListPracticeModel
import javax.inject.Inject

class ReadListPracticeEntityMapper @Inject constructor(
): Mapper<ReadListPracticeEntity, ReadListPracticeModel> {

    override suspend fun map(input: ReadListPracticeEntity): ReadListPracticeModel = with(input) {
        ReadListPracticeModel(id, icon, payAbility, description, title)
    }
}





















