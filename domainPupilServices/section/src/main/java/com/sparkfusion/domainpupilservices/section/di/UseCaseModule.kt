package com.sparkfusion.domainpupilservices.section.di

import com.sparkfusion.domainpupilservices.section.usecase.ReadSectionByIdUseCase
import com.sparkfusion.domainpupilservices.section.usecase.ReadSectionsUseCase
import com.sparkfusion.portdomainservices.pupil.portsection.IReadSectionByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portsection.IReadSectionsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadSectionsUseCaseToIReadSectionsUseCase(readSectionsUseCase: ReadSectionsUseCase): IReadSectionsUseCase

    @Binds
    fun bindReadSectionByIdUseCaseToIReadSectionByIdUseCase(readSectionByIdUseCase: ReadSectionByIdUseCase): IReadSectionByIdUseCase
}





















