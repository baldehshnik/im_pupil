package com.sparkfusion.domain.pupil.account.di

import com.sparkfusion.domain.pupil.account.usecase.ReadPupilAccountUseCase
import com.sparkfusion.domain.pupil.port.portaccount.usecase.IReadPupilAccountUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadPupilAccountUseCaseToIReadPupilAccountUseCase(readPupilAccountUseCase: ReadPupilAccountUseCase): IReadPupilAccountUseCase
}


























