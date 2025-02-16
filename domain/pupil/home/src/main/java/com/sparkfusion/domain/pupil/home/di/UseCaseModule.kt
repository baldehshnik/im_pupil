package com.sparkfusion.domain.pupil.home.di

import com.sparkfusion.domain.pupil.home.usecase.ReadEventsUseCase
import com.sparkfusion.domain.pupil.port.porthome.usecase.IReadEventsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadEventsUseCaseToIReadEventsUseCase(readEventsUseCase: ReadEventsUseCase): IReadEventsUseCase
}




















