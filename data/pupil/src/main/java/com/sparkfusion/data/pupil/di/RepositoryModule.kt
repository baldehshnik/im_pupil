package com.sparkfusion.data.pupil.di

import com.sparkfusion.data.pupil.repository.AuthRepository
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindAuthRepositoryToIAuthRepository(authRepository: AuthRepository): IAuthRepository
}
























