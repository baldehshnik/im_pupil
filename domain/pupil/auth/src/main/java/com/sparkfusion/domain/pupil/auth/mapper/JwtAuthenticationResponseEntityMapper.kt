package com.sparkfusion.domain.pupil.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portauth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.domain.pupil.port.portauth.model.JwtAuthenticationResponseModel
import javax.inject.Inject

internal class JwtAuthenticationResponseEntityMapper @Inject constructor(
) : Mapper<JwtAuthenticationResponseEntity, JwtAuthenticationResponseModel> {

    override suspend fun map(input: JwtAuthenticationResponseEntity): JwtAuthenticationResponseModel =
        with(input) {
            JwtAuthenticationResponseModel(accessToken, refreshToken)
        }
}