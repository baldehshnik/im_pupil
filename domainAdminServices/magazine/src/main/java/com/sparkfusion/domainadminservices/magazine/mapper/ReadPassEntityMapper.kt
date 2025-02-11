package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadPassEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadPassModel
import javax.inject.Inject

internal class ReadPassEntityMapper @Inject constructor(
): Mapper<ReadPassEntity, ReadPassModel> {

    override suspend fun map(input: ReadPassEntity): ReadPassModel = with(input) {
        ReadPassModel(id, date, status)
    }
}