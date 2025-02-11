package com.sparkfusion.domain.pupil.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portauth.entity.AddPupilEntity
import com.sparkfusion.domain.pupil.port.portauth.model.AddPupilModel
import javax.inject.Inject

internal class AddPupilModelMapper @Inject constructor(
): Mapper<AddPupilModel, AddPupilEntity> {

    override suspend fun map(input: AddPupilModel): AddPupilEntity = with(input) {
        AddPupilEntity(code, email, password, institutionId)
    }
}