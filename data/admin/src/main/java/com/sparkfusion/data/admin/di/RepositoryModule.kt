package com.sparkfusion.data.admin.di

import com.sparkfusion.data.admin.repository.auth.AdminAuthRepository
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAdminAuthRepositoryToIAdminAuthRepository(adminAuthRepository: AdminAuthRepository): IAdminAuthRepository
}

















