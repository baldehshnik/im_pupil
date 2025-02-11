package com.sparkfusion.domainadminservices.practice.di

import com.sparkfusion.domainadminservices.practice.usecase.CreatePracticeUseCase
import com.sparkfusion.domainadminservices.practice.usecase.DeletePracticeByIdUseCase
import com.sparkfusion.domainadminservices.practice.usecase.ReadPracticeByIdUseCase
import com.sparkfusion.domainadminservices.practice.usecase.ReadPracticesUseCase
import com.sparkfusion.domainadminservices.practice.usecase.UpdatePracticeUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.ICreatePracticeUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IDeletePracticeByIdUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IReadPracticeByIdUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IReadPracticesUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IUpdatePracticeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindCreatePracticeUseCaseToICreatePracticeUseCase(createPracticeUseCase: CreatePracticeUseCase): ICreatePracticeUseCase

    @Binds
    fun bindDeletePracticeByIdUseCaseToIDeletePracticeByIdUseCase(deletePracticeByIdUseCase: DeletePracticeByIdUseCase): IDeletePracticeByIdUseCase

    @Binds
    fun bindReadPracticeByIdUseCaseToIReadPracticeByIdUseCase(readPracticeByIdUseCase: ReadPracticeByIdUseCase): IReadPracticeByIdUseCase

    @Binds
    fun bindReadPracticesUseCaseToIReadPracticesUseCase(readPracticesUseCase: ReadPracticesUseCase): IReadPracticesUseCase

    @Binds
    fun bindUpdatePracticeUseCaseToIUpdatePracticeUseCase(updatePracticeUseCase: UpdatePracticeUseCase): IUpdatePracticeUseCase
}

























