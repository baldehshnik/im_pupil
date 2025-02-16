package com.sparkfusion.data.pupil.di

import com.sparkfusion.data.pupil.repository.AccountRepository
import com.sparkfusion.data.pupil.repository.AuthRepository
import com.sparkfusion.data.pupil.repository.EventDetailsRepository
import com.sparkfusion.data.pupil.repository.HomeRepository
import com.sparkfusion.dataport.pupil.portaccount.IAccountRepository
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.dataport.pupil.porteventdetails.IEventDetailsRepository
import com.sparkfusion.dataport.pupil.porthome.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindAuthRepositoryToIAuthRepository(authRepository: AuthRepository): IAuthRepository

    @Binds
    fun bindHomeRepositoryIHomeRepository(homeRepository: HomeRepository): IHomeRepository

    @Binds
    fun bindEventDetailsRepositoryToIEventDetailsRepository(eventDetailsRepository: EventDetailsRepository): IEventDetailsRepository

    @Binds
    fun bindAccountRepositoryIAccountRepository(accountRepository: AccountRepository): IAccountRepository
}
























