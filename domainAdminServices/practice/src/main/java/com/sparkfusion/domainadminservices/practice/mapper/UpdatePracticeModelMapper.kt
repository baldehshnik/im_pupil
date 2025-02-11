package com.sparkfusion.domainadminservices.practice.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portpractice.entity.UpdatePracticeEntity
import com.sparkfusion.portdomainservices.admin.portpractice.model.UpdatePracticeModel
import javax.inject.Inject

internal class UpdatePracticeModelMapper @Inject constructor(
    private val updateInformationBlockModelMapper: UpdateInformationBlockModelMapper,
    private val updateRelocationModelMapper: UpdateRelocationModelMapper
) : Mapper<UpdatePracticeModel, UpdatePracticeEntity> {

    override suspend fun map(input: UpdatePracticeModel): UpdatePracticeEntity = with(input) {
        UpdatePracticeEntity(
            id,
            icon,
            payAbility,
            description,
            workType,
            title,
            website,
            informationBlocks.map { updateInformationBlockModelMapper.map(it) }.toSet(),
            relocations.map { updateRelocationModelMapper.map(it) }.toSet()
        )
    }
}
















