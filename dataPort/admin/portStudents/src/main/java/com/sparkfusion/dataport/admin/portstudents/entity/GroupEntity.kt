package com.sparkfusion.dataport.admin.portstudents.entity

import com.sparkfusion.data.commonentity.CommonGroupDataEntity

data class GroupEntity(
    override val id: Int,
    override val name: String,
    override val course: Int
): CommonGroupDataEntity
