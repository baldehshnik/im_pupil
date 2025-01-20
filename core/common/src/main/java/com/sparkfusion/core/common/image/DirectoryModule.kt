package com.sparkfusion.core.common.image

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DirectoryModule {

    @Singleton
    @CacheDirectory
    @Provides
    fun provideCacheDirectory(@ApplicationContext context: Context): File {
        return context.cacheDir
    }
}
