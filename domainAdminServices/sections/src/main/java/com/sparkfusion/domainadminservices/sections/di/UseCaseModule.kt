package com.sparkfusion.domainadminservices.sections.di

import com.sparkfusion.domainadminservices.sections.usecase.CreateSectionUseCase
import com.sparkfusion.domainadminservices.sections.usecase.DeleteSectionByIdUseCase
import com.sparkfusion.domainadminservices.sections.usecase.ReadSectionByIdUseCase
import com.sparkfusion.domainadminservices.sections.usecase.ReadSectionsUseCase
import com.sparkfusion.domainadminservices.sections.usecase.UpdateSectionUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.ICreateSectionUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IDeleteSectionByIdUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionByIdUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionsUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IUpdateSectionUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCreateSectionUseCaseToICreateSectionUseCase(createSectionUseCase: CreateSectionUseCase): ICreateSectionUseCase

    @Binds
    fun bindDeleteSectionByIdUseCaseToIDeleteSectionByIdUseCase(deleteSectionByIdUseCase: DeleteSectionByIdUseCase): IDeleteSectionByIdUseCase

    @Binds
    fun bindReadSectionByIdUseCaseToIReadSectionByIdUseCase(readSectionByIdUseCase: ReadSectionByIdUseCase): IReadSectionByIdUseCase

    @Binds
    fun bindReadSectionsUseCaseToIReadSectionsUseCase(readSectionsUseCase: ReadSectionsUseCase): IReadSectionsUseCase

    @Binds
    fun bindUpdateSectionUseCaseToIUpdateSectionUseCase(updateSectionUseCase: UpdateSectionUseCase): IUpdateSectionUseCase
}























