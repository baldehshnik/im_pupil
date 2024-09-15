package com.sparkfusion.data.base.db.initializer

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.data.base.db.dao.ServiceDao
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ServicesTableInitializer @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val provider: Provider<ServiceDao>,
    @ApplicationContext private val context: Context
) : RoomDatabase.Callback() {

    private val ioScope = CoroutineScope(ioDispatcher + SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val services = getInitializeServicesList(context)
        ioScope.launch {
            provider.get().insertServices(*services.toTypedArray())
        }
    }
}