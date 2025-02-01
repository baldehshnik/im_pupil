package com.sparkfusion.dataport.admin.portstudents.entity

import com.sparkfusion.data.commonentity.CommonPupilDataEntity

data class PupilEntity(
    override val id: Int,
    override val icon: String?,
    override val code: String,
    override val status: Int,
): CommonPupilDataEntity
