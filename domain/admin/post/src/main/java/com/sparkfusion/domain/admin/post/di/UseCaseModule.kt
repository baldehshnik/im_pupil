package com.sparkfusion.domain.admin.post.di

import com.sparkfusion.domain.admin.port.portpost.IAddInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.IDeleteInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.IReadInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.IUpdateInstitutionEventUseCase
import com.sparkfusion.domain.admin.post.usecase.AddInstitutionEventUseCase
import com.sparkfusion.domain.admin.post.usecase.DeleteInstitutionEventUseCase
import com.sparkfusion.domain.admin.post.usecase.ReadInstitutionEventUseCase
import com.sparkfusion.domain.admin.post.usecase.UpdateInstitutionEventUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadInstitutionEventUseCaseToIReadInstitutionEventUseCase(readInstitutionEventUseCase: ReadInstitutionEventUseCase): IReadInstitutionEventUseCase

    @Binds
    fun bindAddInstitutionEventUseCaseToIAddInstitutionEventUseCase(addInstitutionEventUseCase: AddInstitutionEventUseCase): IAddInstitutionEventUseCase

    @Binds
    fun bindUpdateInstitutionEventUseCaseToIUpdateInstitutionEventUseCase(updateInstitutionEventUseCase: UpdateInstitutionEventUseCase): IUpdateInstitutionEventUseCase

    @Binds
    fun bindDeleteInstitutionEventUseCaseToIDeleteInstitutionEventUseCase(deleteInstitutionEventUseCase: DeleteInstitutionEventUseCase): IDeleteInstitutionEventUseCase
}


























