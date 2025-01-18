package com.sparkfusion.domain.admin.auth.di

import com.sparkfusion.domain.admin.auth.usecase.ReadInstitutionUseCase
import com.sparkfusion.domain.admin.auth.usecase.AdminSignInUseCase
import com.sparkfusion.domain.admin.auth.usecase.CheckAdminTokenUseCase
import com.sparkfusion.domain.admin.auth.usecase.SignUpUseCase
import com.sparkfusion.domain.admin.port.portauth.IReadInstitutionUseCase
import com.sparkfusion.domain.admin.port.portauth.IAdminSignInUseCase
import com.sparkfusion.domain.admin.port.portauth.ICheckAdminTokenUseCase
import com.sparkfusion.domain.admin.port.portauth.ISignUpUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindSignUpUseCaseToISignUpUseCase(signUpUseCase: SignUpUseCase): ISignUpUseCase

    @Binds
    fun bindReadInstitutionUseCaseToIReadInstitutionUseCase(readInstitutionUseCase: ReadInstitutionUseCase): IReadInstitutionUseCase

    @Binds
    fun bindAdminSingInUseCaseToIAdminSignInUseCase(singInUseCase: AdminSignInUseCase): IAdminSignInUseCase

    @Binds
    fun bindCheckAdminTokenUseCaseToICheckAdminTokenUseCase(checkAdminTokenUseCase: CheckAdminTokenUseCase): ICheckAdminTokenUseCase
}

















