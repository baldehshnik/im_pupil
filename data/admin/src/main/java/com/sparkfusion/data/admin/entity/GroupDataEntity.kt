package com.sparkfusion.data.admin.entity

import com.sparkfusion.data.commonentity.CommonGroupDataEntity

internal data class GroupDataEntity(
    override val id: Int,
    override val name: String,
    override val course: Int
): CommonGroupDataEntity
