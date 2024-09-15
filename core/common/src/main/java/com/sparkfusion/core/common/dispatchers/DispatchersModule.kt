package com.sparkfusion.core.common.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers as KotlinxCoroutinesDispatchers

@InstallIn(SingletonComponent::class)
@Module
object DispatchersModule {

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): MainCoroutineDispatcher {
        return KotlinxCoroutinesDispatchers.Main
    }

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher {
        return KotlinxCoroutinesDispatchers.IO
    }

    @Provides
    @Singleton
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return KotlinxCoroutinesDispatchers.Default
    }
}