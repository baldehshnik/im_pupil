package com.sparkfusion.domain.admin.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portAuth.entity.SignInAdminEntity
import com.sparkfusion.domain.admin.port.portauth.SignInModel
import javax.inject.Inject

class SignInModelMapper @Inject constructor(
): Mapper<SignInModel, SignInAdminEntity> {

    override suspend fun map(input: SignInModel): SignInAdminEntity = with(input) {
        SignInAdminEntity(email, password)
    }
}