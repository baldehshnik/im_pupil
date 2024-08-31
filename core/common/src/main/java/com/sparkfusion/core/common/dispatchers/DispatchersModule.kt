package com.sparkfusion.core.common.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.Dispatchers as KotlinxCoroutinesDispatchers

@InstallIn(SingletonComponent::class)
@Module
object DispatchersModule {

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): MainCoroutineDispatcher {
        return KotlinxCoroutinesDispatchers.Main
    }

    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher {
        return KotlinxCoroutinesDispatchers.IO
    }

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return KotlinxCoroutinesDispatchers.Default
    }
}