package com.sparkfusion.data.base.db.entity

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val SERVICES_TABLE = "services"

@Entity(
    tableName = SERVICES_TABLE,
    indices = [
        Index("title", unique = true),
        Index("image_id", unique = true)
    ]
)
data class ServiceEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("image_id")
    @DrawableRes
    val imageId: Int,

    @ColumnInfo("position")
    val position: Int,

    @ColumnInfo("enabled")
    val isEnabled: Boolean
)
