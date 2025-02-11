package com.sparkfusion.domain.pupil.auth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.domain.pupil.auth.mapper.AddPupilModelMapper
import com.sparkfusion.domain.pupil.port.portauth.model.AddPupilModel
import com.sparkfusion.domain.pupil.port.portauth.usecase.IRegisterPupilUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class RegisterPupilUseCase @Inject constructor(
    private val repository: IAuthRepository,
    private val addPupilModelMapper: AddPupilModelMapper
) : IRegisterPupilUseCase {

    override suspend fun signUpPupil(addPupil: AddPupilModel): Answer<Unit> {
        return repository.signUpPupil(addPupilModelMapper.map(addPupil))
    }
}























