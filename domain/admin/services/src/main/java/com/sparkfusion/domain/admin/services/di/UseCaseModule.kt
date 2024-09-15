package com.sparkfusion.domain.admin.services.di

import com.sparkfusion.domain.admin.port.portservices.IReadNewsUseCase
import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.services.usecase.ReadNewsUseCase
import com.sparkfusion.domain.admin.services.usecase.ReadServicesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadServicesUseCaseToIReadNewsUseCase(readServicesUseCase: ReadServicesUseCase): IReadServicesUseCase

    @Binds
    fun bindReadNewsUseCaseToIReadNewsUseCase(readNewsUseCase: ReadNewsUseCase): IReadNewsUseCase
}