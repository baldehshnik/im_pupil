package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.repository.feature.AdminServicesRepository
import com.sparkfusion.dataport.admin.portservices.IAdminServicesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FeaturesModule {

    @Binds
    fun bindAdminServicesRepositoryToIAdminServicesRepository(
        adminServicesRepository: AdminServicesRepository
    ): IAdminServicesRepository
}