package com.sparkfusion.core.hilt_core

import android.content.Context
import androidx.room.Room
import com.sparkfusion.data.base.db.DATABASE_NAME
import com.sparkfusion.data.base.db.ImPupilDatabase
import com.sparkfusion.data.base.db.dao.ServiceDao
import com.sparkfusion.data.base.db.initializer.ServicesTableInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideImPupilDatabase(
        @ApplicationContext context: Context,
        callback: ServicesTableInitializer
    ): ImPupilDatabase {
        return Room.databaseBuilder(
            context,
            ImPupilDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideServicesDao(database: ImPupilDatabase): ServiceDao {
        return database.serviceDao
    }
}