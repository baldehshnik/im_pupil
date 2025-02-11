package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.repository.AboutRepository
import com.sparkfusion.data.common.repository.InstitutionEventRepository
import com.sparkfusion.data.common.repository.NewsRepository
import com.sparkfusion.data.common.repository.ServiceRepository
import com.sparkfusion.dataPort.common.portinstitutionevent.IInstitutionEventRepository
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import com.sparkfusion.dataport.common.portabout.IAboutRepository
import com.sparkfusion.dataport.common.portservices.IServiceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindNewsRepositoryToINewsRepository(newsRepository: NewsRepository): INewsRepository

    @Binds
    fun bindInstitutionEventRepositoryToIInstitutionEventRepository(institutionEventRepository: InstitutionEventRepository): IInstitutionEventRepository

    @Binds
    fun bindAboutRepositoryToIAboutRepository(aboutRepository: AboutRepository): IAboutRepository

    @Binds
    fun bindServiceRepositoryIServiceRepository(serviceRepository: ServiceRepository): IServiceRepository
}




























