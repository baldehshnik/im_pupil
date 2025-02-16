package com.sparkfusion.domain.pupil.services.di

import com.sparkfusion.domain.pupil.port.portservices.usecase.IReadNewsUseCase
import com.sparkfusion.domain.pupil.port.portservices.usecase.IReadServicesUseCase
import com.sparkfusion.domain.pupil.services.usecase.ReadNewsUseCase
import com.sparkfusion.domain.pupil.services.usecase.ReadServicesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadNewsUseCaseToIReadNewsUseCase(readNewsUseCase: ReadNewsUseCase): IReadNewsUseCase

    @Binds
    fun bindReadServicesUseCaseToIReadServicesUseCase(readServicesUseCase: ReadServicesUseCase): IReadServicesUseCase
}



















