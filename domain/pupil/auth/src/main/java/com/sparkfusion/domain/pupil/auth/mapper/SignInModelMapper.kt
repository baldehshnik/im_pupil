package com.sparkfusion.domain.pupil.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portauth.entity.SignInEntity
import com.sparkfusion.domain.pupil.port.portauth.model.SignInModel
import javax.inject.Inject

internal class SignInModelMapper @Inject constructor(
): Mapper<SignInModel, SignInEntity> {

    override suspend fun map(input: SignInModel): SignInEntity = with(input) {
        SignInEntity(email, password)
    }
}