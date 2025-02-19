package com.sparkfusion.domainpupilservices.about.di

import com.sparkfusion.domainpupilservices.about.usecase.ReadAboutBlocksUseCase
import com.sparkfusion.portdomainservices.pupil.portabout.usecase.IReadAboutBlocksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadAboutBlocksUseCaseToIReadAboutBlocksUseCase(readAboutBlocksUseCase: ReadAboutBlocksUseCase): IReadAboutBlocksUseCase
}























