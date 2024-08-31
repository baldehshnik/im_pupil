package com.sparkfusion.data.base.db.initializer

import com.sparkfusion.core.resource.R
import com.sparkfusion.data.base.db.entity.ServiceEntity

fun getInitializeServicesList(): List<ServiceEntity> {
    return listOf(
        ServiceEntity(title = "Magazine", imageId =  R.drawable.magazine_service_icon, position =  1, isEnabled =  true),
        ServiceEntity(title = "Notifications", imageId =  R.drawable.notifications_service_icon, position =  2, isEnabled =  true),
        ServiceEntity(title = "Schedule", imageId =  R.drawable.schedule_service_icon, position =  3, isEnabled =  true),
        ServiceEntity(title = "Statistics", imageId =  R.drawable.statistics_service_icon, position =  4, isEnabled =  true),
        ServiceEntity(title = "Messenger", imageId =  R.drawable.messenger_service_icon, position =  5, isEnabled =  true),
        ServiceEntity(title = "Sections", imageId =  R.drawable.sections_service_icon, position =  6, isEnabled =  true),
        ServiceEntity(title = "Session", imageId =  R.drawable.session_service_icon, position =  7, isEnabled =  true),
        ServiceEntity(title = "Practice", imageId =  R.drawable.practice_service_icon, position =  8, isEnabled =  true),
        ServiceEntity(title = "About", imageId =  R.drawable.about_service_icon, position =  9, isEnabled =  true),
        ServiceEntity(title = "Students", imageId =  R.drawable.students_service_icon, position =  10, isEnabled =  true),
        ServiceEntity(title = "Settings", imageId =  R.drawable.settings_service_icon, position =  11, isEnabled =  true)
    )
}