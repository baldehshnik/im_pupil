package com.sparkfusion.domain.admin.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portAuth.entity.SignUpAdminEntity
import com.sparkfusion.domain.admin.port.portauth.SignUpModel
import javax.inject.Inject

class SignUpModelMapper @Inject constructor(
): Mapper<SignUpModel, SignUpAdminEntity> {

    override suspend fun map(input: SignUpModel): SignUpAdminEntity = with(input) {
        return@with SignUpAdminEntity(firstname, lastname, patronymic, email, password, educationalInstitutionAbbreviation)
    }
}
















