package com.sparkfusion.data.pupil.di

import com.sparkfusion.data.pupil.repository.AboutRepository
import com.sparkfusion.data.pupil.repository.AccountRepository
import com.sparkfusion.data.pupil.repository.AuthRepository
import com.sparkfusion.data.pupil.repository.EventDetailsRepository
import com.sparkfusion.data.pupil.repository.HomeRepository
import com.sparkfusion.data.pupil.repository.PracticeRepository
import com.sparkfusion.data.pupil.repository.SectionRepository
import com.sparkfusion.data.pupil.repository.StudentRepository
import com.sparkfusion.dataport.pupil.portabout.IAboutRepository
import com.sparkfusion.dataport.pupil.portaccount.IAccountRepository
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.dataport.pupil.porteventdetails.IEventDetailsRepository
import com.sparkfusion.dataport.pupil.porthome.IHomeRepository
import com.sparkfusion.dataport.pupil.portpractice.IPracticeRepository
import com.sparkfusion.dataport.pupil.portsection.ISectionRepository
import com.sparkfusion.dataport.pupil.portstudents.IStudentRepository
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
    fun bindHomeRepositoryToIHomeRepository(homeRepository: HomeRepository): IHomeRepository

    @Binds
    fun bindEventDetailsRepositoryToIEventDetailsRepository(eventDetailsRepository: EventDetailsRepository): IEventDetailsRepository

    @Binds
    fun bindAccountRepositoryToIAccountRepository(accountRepository: AccountRepository): IAccountRepository

    @Binds
    fun bindPracticeRepositoryToIPracticeRepository(practiceRepository: PracticeRepository): IPracticeRepository

    @Binds
    fun bindAboutRepositoryToIAboutRepository(aboutRepository: AboutRepository): IAboutRepository

    @Binds
    fun bindSectionRepositoryToISectionRepository(sectionRepository: SectionRepository): ISectionRepository

    @Binds
    fun bindStudentRepositoryToIStudentRepository(studentRepository: StudentRepository): IStudentRepository
}
























