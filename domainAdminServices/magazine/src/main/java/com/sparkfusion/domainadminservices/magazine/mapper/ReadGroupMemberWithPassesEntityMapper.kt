package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberWithPassesEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel
import javax.inject.Inject

internal class ReadGroupMemberWithPassesEntityMapper @Inject constructor(
    private val readGroupMemberEntityMapper: ReadGroupMemberEntityMapper,
    private val readPassEntityMapper: ReadPassEntityMapper
): Mapper<ReadGroupMemberWithPassesEntity, ReadGroupMemberWithPassesModel> {

    override suspend fun map(input: ReadGroupMemberWithPassesEntity): ReadGroupMemberWithPassesModel = with(input) {
        ReadGroupMemberWithPassesModel(
            readGroupMemberEntityMapper.map(getGroupMemberDto),
            readPassEntityMapper.map(getPassDto)
        )
    }
}






















