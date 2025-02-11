package com.sparkfusion.data.common.entity

import com.sparkfusion.data.commonentity.CommonInstitutionEventDataEntity

internal data class InstitutionEventDataEntity(
    override val id: Int,
    override val title: String,
    override val description: String,
    override val image: String,
    override val duration: Int,
    override val type: Int
): CommonInstitutionEventDataEntity
