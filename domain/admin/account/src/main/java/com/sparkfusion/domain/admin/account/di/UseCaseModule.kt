package com.sparkfusion.domain.admin.account.di

import com.sparkfusion.domain.admin.account.usecase.ReadAdminAccountUseCase
import com.sparkfusion.domain.admin.account.usecase.ReadAllAdminsOfInstitutionUseCase
import com.sparkfusion.domain.admin.account.usecase.ReadInstitutionUseCase
import com.sparkfusion.domain.admin.account.usecase.UpdateAccountImageUseCase
import com.sparkfusion.domain.admin.port.portaccount.IReadAdminAccountUseCase
import com.sparkfusion.domain.admin.port.portaccount.IReadAllAdminsOfInstitutionUseCase
import com.sparkfusion.domain.admin.port.portaccount.IReadInstitutionUseCase
import com.sparkfusion.domain.admin.port.portaccount.IUpdateAccountImageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadAdminAccountUseCaseToIAdminAccountUseCase(readAdminAccountUseCase: ReadAdminAccountUseCase): IReadAdminAccountUseCase

    @Binds
    fun bindReadInstitutionUseCaseToIReadInstitutionUseCase(readInstitutionUseCase: ReadInstitutionUseCase): IReadInstitutionUseCase

    @Binds
    fun bindReadAllAdminsOfInstitutionUseCaseToIReadAllAdminsOfInstitutionUseCase(readAllAdminsOfInstitutionUseCase: ReadAllAdminsOfInstitutionUseCase): IReadAllAdminsOfInstitutionUseCase

    @Binds
    fun bindUpdateAccountImageUseCaseToIUpdateAccountImageUseCase(updateAccountImageUseCase: UpdateAccountImageUseCase): IUpdateAccountImageUseCase
}



