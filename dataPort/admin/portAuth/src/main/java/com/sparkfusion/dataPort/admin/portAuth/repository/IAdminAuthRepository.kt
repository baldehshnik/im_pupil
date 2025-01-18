package com.sparkfusion.dataPort.admin.portAuth.repository

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portAuth.entity.EducationalInstitutionEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.SignInAdminEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.SignUpAdminEntity

interface IAdminAuthRepository {

    suspend fun signUp(signUpAdminEntity: SignUpAdminEntity): Answer<Unit>
    suspend fun signIn(signInAdminEntity: SignInAdminEntity): Answer<JwtAuthenticationResponseEntity>
    suspend fun checkToken(): Answer<Unit>

    suspend fun readInstitutionByNamePart(namePart: String): Answer<List<EducationalInstitutionEntity>>
}
















