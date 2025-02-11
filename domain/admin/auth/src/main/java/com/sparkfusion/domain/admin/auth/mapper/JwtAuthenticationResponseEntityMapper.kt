package com.sparkfusion.domain.admin.auth.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.admin.portAuth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.domain.admin.port.portauth.JwtAuthenticationModel
import javax.inject.Inject

internal class JwtAuthenticationResponseEntityMapper @Inject constructor(
): Mapper<JwtAuthenticationResponseEntity, JwtAuthenticationModel> {

    override suspend fun map(input: JwtAuthenticationResponseEntity): JwtAuthenticationModel = with(input) {
        JwtAuthenticationModel(accessToken, refreshToken)
    }
}