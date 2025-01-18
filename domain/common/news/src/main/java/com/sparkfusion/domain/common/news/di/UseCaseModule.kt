package com.sparkfusion.domain.common.news.di

import com.sparkfusion.domain.common.news.usecase.ReadNewsInfoUseCase
import com.sparkfusion.domain.common.portnews.IReadNewsInfoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadNewsInfoUseCaseToIReadNewsInfoUseCase(readNewsInfoUseCase: ReadNewsInfoUseCase): IReadNewsInfoUseCase
}

























