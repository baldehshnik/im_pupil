package com.sparkfusion.data.base.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sparkfusion.data.base.db.entity.SERVICES_TABLE
import com.sparkfusion.data.base.db.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {

    @Insert
    suspend fun insertServices(vararg services: ServiceEntity)

    @Query("SELECT * FROM $SERVICES_TABLE WHERE enabled = 1 ORDER BY position")
    fun readEnabledServices(): Flow<List<ServiceEntity>>
}