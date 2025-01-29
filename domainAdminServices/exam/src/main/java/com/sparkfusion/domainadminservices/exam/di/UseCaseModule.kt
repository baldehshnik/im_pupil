package com.sparkfusion.domainadminservices.exam.di

import com.sparkfusion.domainadminservices.exam.usecase.CreateExamUseCase
import com.sparkfusion.domainadminservices.exam.usecase.DeleteExamsUseCase
import com.sparkfusion.domainadminservices.exam.usecase.ReadExamByIdUseCase
import com.sparkfusion.domainadminservices.exam.usecase.ReadExamsUseCase
import com.sparkfusion.domainadminservices.exam.usecase.ReadGroupByNamePartUseCase
import com.sparkfusion.domainadminservices.exam.usecase.UpdateExamUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.ICreateExamUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IDeleteExamsUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadExamByIdUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadExamsUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadGroupByNamePartUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IUpdateExamUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCreateExamUseCaseToICreateExamUseCase(createExamUseCase: CreateExamUseCase): ICreateExamUseCase

    @Binds
    fun bindDeleteExamsUseCaseToIDeleteExamsUseCase(deleteExamsUseCase: DeleteExamsUseCase): IDeleteExamsUseCase

    @Binds
    fun bindReadExamByIdUseCaseToIReadExamByIdUseCase(readExamByIdUseCase: ReadExamByIdUseCase): IReadExamByIdUseCase

    @Binds
    fun bindReadExamsUseCaseToIReadExamsUseCase(readExamsUseCase: ReadExamsUseCase): IReadExamsUseCase

    @Binds
    fun bindReadGroupByNamePartUseCaseToIReadGroupByNamePartUseCase(readGroupByNamePartUseCase: ReadGroupByNamePartUseCase): IReadGroupByNamePartUseCase

    @Binds
    fun bindUpdateExamUseCaseToIUpdateExamUseCase(updateExamUseCase: UpdateExamUseCase): IUpdateExamUseCase
}


























