package com.sparkfusion.data.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sparkfusion.data.base.db.dao.ServiceDao
import com.sparkfusion.data.base.db.entity.ServiceEntity

const val DATABASE_NAME = "im_pupil_database"

@Database(entities = [ServiceEntity::class], version = 1, exportSchema = false)
abstract class ImPupilDatabase : RoomDatabase() {

    abstract val serviceDao: ServiceDao
}