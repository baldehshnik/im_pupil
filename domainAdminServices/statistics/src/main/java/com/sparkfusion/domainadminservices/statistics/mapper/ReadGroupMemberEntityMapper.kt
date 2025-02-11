package com.sparkfusion.domainadminservices.statistics.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadGroupMemberEntity
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadGroupMemberModel
import javax.inject.Inject

internal class ReadGroupMemberEntityMapper @Inject constructor(
    private val pupilEntityMapper: PupilEntityMapper
): Mapper<ReadGroupMemberEntity, ReadGroupMemberModel> {

    override suspend fun map(input: ReadGroupMemberEntity): ReadGroupMemberModel = with(input) {
        ReadGroupMemberModel(id, firstname, lastname, patronymic, prefect, code, pupil?.let { pupilEntityMapper.map(it) })
    }
}