package com.sparkfusion.dataport.pupil.portauth

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portauth.entity.AddPupilEntity
import com.sparkfusion.dataport.pupil.portauth.entity.EducationalInstitutionEntity
import com.sparkfusion.dataport.pupil.portauth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.dataport.pupil.portauth.entity.SignInEntity

interface IAuthRepository {

    suspend fun signUpPupil(addPupil: AddPupilEntity): Answer<Unit>

    suspend fun signInPupil(signIn: SignInEntity): Answer<JwtAuthenticationResponseEntity>

    suspend fun checkAccessToken(): Answer<Unit>

    suspend fun readInstitutionByNamePart(namePart: String): Answer<List<EducationalInstitutionEntity>>
}



















