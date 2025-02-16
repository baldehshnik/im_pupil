package com.sparkfusion.domain.pupil.eventdetails.di

import com.sparkfusion.domain.pupil.eventdetails.usecase.EventDetailsUseCase
import com.sparkfusion.domain.pupil.port.porteventdetails.usecase.IEventDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindEventDetailsUseCaseToIEventDetailsUseCase(eventDetailsUseCase: EventDetailsUseCase): IEventDetailsUseCase
}




















