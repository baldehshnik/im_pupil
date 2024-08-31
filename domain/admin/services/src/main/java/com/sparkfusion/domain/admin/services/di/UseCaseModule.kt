package com.sparkfusion.domain.admin.services.di

import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.services.usecase.ReadServicesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadServicesUseCaseToIReadNewsUseCase(readServicesUseCase: ReadServicesUseCase): IReadServicesUseCase
}