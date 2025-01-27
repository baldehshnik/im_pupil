package com.sparkfusion.domainadminservices.about.di

import com.sparkfusion.domainadminservices.about.usecase.ReadAboutBlocksUseCase
import com.sparkfusion.domainadminservices.about.usecase.UpdateAboutBlockUseCase
import com.sparkfusion.portdomainservices.admin.portabout.IReadAboutBlocksUseCase
import com.sparkfusion.portdomainservices.admin.portabout.IUpdateAboutBlockUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadAboutBlocksUseCaseToIReadAboutBlocksUseCase(readAboutBlocksUseCase: ReadAboutBlocksUseCase): IReadAboutBlocksUseCase

    @Binds
    fun bindUpdateAboutBlockUseCaseToIUpdateBlockUseCase(updateAboutBlockUseCase: UpdateAboutBlockUseCase): IUpdateAboutBlockUseCase
}





















