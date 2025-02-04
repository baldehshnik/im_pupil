package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.ReadPracticeEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadPracticeModel
import javax.inject.Inject

class ReadPracticeEntityMapper @Inject constructor(
    private val informationBlockEntityMapper: InformationBlockEntityMapper,
    private val relocationEntityMapper: RelocationEntityMapper
): Mapper<ReadPracticeEntity, ReadPracticeModel> {

    override suspend fun map(input: ReadPracticeEntity): ReadPracticeModel = with(input) {
        ReadPracticeModel(
            id,
            icon,
            payAbility,
            description,
            workType,
            title,
            website,
            informationBlocks.map { informationBlockEntityMapper.map(it) }.toSet(),
            relocations.map { relocationEntityMapper.map(it) }.toSet()
        )
    }
}























