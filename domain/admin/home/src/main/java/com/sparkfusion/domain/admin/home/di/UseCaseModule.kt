package com.sparkfusion.domain.admin.home.di

import com.sparkfusion.domain.admin.home.usecase.ReadInstitutionEventsUseCase
import com.sparkfusion.domain.admin.home.usecase.ReadAccountUseCase
import com.sparkfusion.domain.admin.port.porthome.IReadAccountUseCase
import com.sparkfusion.domain.admin.port.porthome.IReadInstitutionEventsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadInstitutionEventsUseCaseToIReadInstitutionEventsUseCase(institutionEventsUseCase: ReadInstitutionEventsUseCase): IReadInstitutionEventsUseCase

    @Binds
    fun bindReadAccountUseCaseToIReadAccountUseCase(readAccountUseCase: ReadAccountUseCase): IReadAccountUseCase
}






















