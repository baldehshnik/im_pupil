package com.sparkfusion.domain.admin.confirmation.di

import com.sparkfusion.domain.admin.confirmation.usecase.ConfirmAdminUseCase
import com.sparkfusion.domain.admin.confirmation.usecase.ConfirmPupilUseCase
import com.sparkfusion.domain.admin.confirmation.usecase.ReadUnconfirmedAdminsUseCase
import com.sparkfusion.domain.admin.confirmation.usecase.ReadUnconfirmedPupilsUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.IConfirmAdminUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.IConfirmPupilUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.IReadUnconfirmedAdminsUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.IReadUnconfirmedPupilsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindConfirmAdminUseCaseToIConfirmAdminUseCase(confirmAdminUseCase: ConfirmAdminUseCase): IConfirmAdminUseCase

    @Binds
    fun bindConfirmPupilUseCaseToIConfirmPupilUseCase(confirmPupilUseCase: ConfirmPupilUseCase): IConfirmPupilUseCase

    @Binds
    fun bindReadUnconfirmedAdminsUseCaseToIReadUnconfirmedAdminsUseCase(readUnconfirmedAdminsUseCase: ReadUnconfirmedAdminsUseCase): IReadUnconfirmedAdminsUseCase

    @Binds
    fun bindReadUnconfirmedPupilsUseCaseToIReadUnconfirmedPupilsUseCase(readUnconfirmedPupilsUseCase: ReadUnconfirmedPupilsUseCase): IReadUnconfirmedPupilsUseCase
}




























