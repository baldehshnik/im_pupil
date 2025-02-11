package com.sparkfusion.domain.pupil.auth.di

import com.sparkfusion.domain.pupil.auth.usecase.CheckAccessTokenUseCase
import com.sparkfusion.domain.pupil.auth.usecase.LoginPupilUseCase
import com.sparkfusion.domain.pupil.auth.usecase.ReadInstitutionByNamePartUseCase
import com.sparkfusion.domain.pupil.auth.usecase.RegisterPupilUseCase
import com.sparkfusion.domain.pupil.port.portauth.usecase.ICheckAccessTokenUseCase
import com.sparkfusion.domain.pupil.port.portauth.usecase.ILoginPupilUseCase
import com.sparkfusion.domain.pupil.port.portauth.usecase.IReadInstitutionByNamePartUseCase
import com.sparkfusion.domain.pupil.port.portauth.usecase.IRegisterPupilUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindCheckAccessTokeUseCaseToICheckAccessTokenUseCase(checkAccessTokenUseCase: CheckAccessTokenUseCase): ICheckAccessTokenUseCase

    @Binds
    fun bindLoginPupilUseCase(loginPupilUseCase: LoginPupilUseCase): ILoginPupilUseCase

    @Binds
    fun bindReadInstitutionByNamePartUseCaseToIReadInstitutionByNamePartUseCase(readInstitutionByNamePartUseCase: ReadInstitutionByNamePartUseCase): IReadInstitutionByNamePartUseCase

    @Binds
    fun bindRegisterPupilUseCaseToIRegisterPupilUseCase(registerPupilUseCase: RegisterPupilUseCase): IRegisterPupilUseCase
}



















