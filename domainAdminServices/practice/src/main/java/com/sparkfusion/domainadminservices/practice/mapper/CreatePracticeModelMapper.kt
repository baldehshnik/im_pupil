package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.CreatePracticeEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.CreatePracticeModel
import javax.inject.Inject

internal class CreatePracticeModelMapper @Inject constructor(
    private val informationBlockModelMapper: InformationBlockModelMapper,
    private val relocationModelMapper: RelocationModelMapper
) : Mapper<CreatePracticeModel, CreatePracticeEntity> {

    override suspend fun map(input: CreatePracticeModel): CreatePracticeEntity = with(input) {
        CreatePracticeEntity(
            payAbility,
            description,
            workType,
            title,
            website,
            informationBlocks.map { informationBlockModelMapper.map(it) }.toSet(),
            relocations.map { relocationModelMapper.map(it) }.toSet()
        )
    }
}


















