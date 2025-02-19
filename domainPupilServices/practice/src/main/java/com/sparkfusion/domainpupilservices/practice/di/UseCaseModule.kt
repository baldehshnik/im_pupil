package com.sparkfusion.domainpupilservices.practice.di

import com.sparkfusion.domainpupilservices.practice.usecase.ReadPracticeByIdUseCase
import com.sparkfusion.domainpupilservices.practice.usecase.ReadPracticesUseCase
import com.sparkfusion.portdomainservices.pupil.portpractice.IReadPracticeByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portpractice.IReadPracticesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadPracticeByIdUseCaseToIReadPracticeByIdUseCase(readPracticeByIdUseCase: ReadPracticeByIdUseCase): IReadPracticeByIdUseCase

    @Binds
    fun bindReadPracticesUseCaseToIReadPracticesUseCase(readPracticesUseCase: ReadPracticesUseCase): IReadPracticesUseCase
}
























