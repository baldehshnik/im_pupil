package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.repository.auth.AdminAuthRepository
import com.sparkfusion.data.admin.repository.institution.AdminAccountRepository
import com.sparkfusion.data.admin.repository.institution.AdminDetailsRepository
import com.sparkfusion.data.admin.repository.institution.AdminInstitutionRepository
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import com.sparkfusion.dataPort.admin.portaccount.IAdminAccountRepository
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portadmindetails.IAdminDetailsRepository
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

    @Binds
    fun bindAdminAccountRepositoryToIAdminAccountRepository(adminAccountRepository: AdminAccountRepository): IAdminAccountRepository

    @Binds
    fun bindAdminDetailsRepositoryToIAdminDetailsRepository(adminDetailsRepository: AdminDetailsRepository): IAdminDetailsRepository
}

















