package com.sparkfusion.domain.admin.admindetails.di

import com.sparkfusion.domain.admin.admindetails.usecase.DeleteAdminUseCase
import com.sparkfusion.domain.admin.admindetails.usecase.ReadAdminDetailsUseCase
import com.sparkfusion.domain.admin.admindetails.usecase.UpdateAdminAccessUseCase
import com.sparkfusion.domain.admin.port.portadmindetails.IDeleteAdminUseCase
import com.sparkfusion.domain.admin.port.portadmindetails.IReadAdminDetailsUseCase
import com.sparkfusion.domain.admin.port.portadmindetails.IUpdateAdminAccessUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindReadAdminDetailsUseCaseToIReadAdminDetailsUseCase(readAdminDetailsUseCase: ReadAdminDetailsUseCase): IReadAdminDetailsUseCase

    @Binds
    fun bindUpdateAdminAccessUseCaseToIUpdateAdminAccessUseCase(updateAdminAccessUseCase: UpdateAdminAccessUseCase): IUpdateAdminAccessUseCase

    @Binds
    fun bindDeleteAdminUseCaseToIDeleteAdminUseCase(deleteAdminUseCase: DeleteAdminUseCase): IDeleteAdminUseCase
}




























