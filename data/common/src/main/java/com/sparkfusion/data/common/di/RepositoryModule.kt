package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.repository.InstitutionEventRepository
import com.sparkfusion.data.common.repository.NewsRepository
import com.sparkfusion.dataPort.common.portinstitutionevent.IInstitutionEventRepository
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindNewsRepositoryToINewsRepository(newsRepository: NewsRepository): INewsRepository

    @Binds
    fun bindInstitutionEventRepositoryToIInstitutionEventRepository(institutionEventRepository: InstitutionEventRepository): IInstitutionEventRepository
}




























