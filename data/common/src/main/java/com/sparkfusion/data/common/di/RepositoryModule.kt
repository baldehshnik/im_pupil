package com.sparkfusion.data.common.di

import com.sparkfusion.data.common.repository.NewsRepository
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindNewsRepositoryModuleToINewsRepositoryModule(newsRepository: NewsRepository): INewsRepository
}




























