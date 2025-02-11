package com.sparkfusion.core.hilt_core

import android.content.Context
import android.content.SharedPreferences
import com.sparkfusion.core.common.shared_preferences.LOGIN_SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SharedPreferencesModule {

    @Provides
    @Singleton
    internal fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}



















