package com.sparkfusion.domainpupilservices.session.di

import com.sparkfusion.domainpupilservices.session.usecase.ReadExamsUseCase
import com.sparkfusion.portdomainservices.pupil.portsession.IReadExamsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadExamsUseCaseToIReadExamsUseCase(readExamsUseCase: ReadExamsUseCase): IReadExamsUseCase
}