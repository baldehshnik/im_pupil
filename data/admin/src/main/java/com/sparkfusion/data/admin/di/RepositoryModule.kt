package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.repository.auth.AdminAuthRepository
import com.sparkfusion.data.admin.repository.institution.AdminInstitutionRepository
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAdminAuthRepositoryToIAdminAuthRepository(adminAuthRepository: AdminAuthRepository): IAdminAuthRepository

    @Binds
    fun bindAdminInstitutionRepositoryToIAdminInstitutionRepository(adminInstitutionRepository: AdminInstitutionRepository): IAdminInstitutionRepository
}

















