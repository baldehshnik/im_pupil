package com.sparkfusion.data.base.db.initializer

import android.content.Context
import com.sparkfusion.core.resource.R
import com.sparkfusion.data.base.db.entity.ServiceEntity

internal fun getInitializeServicesList(context: Context): List<ServiceEntity> {
    return listOf(
        ServiceEntity(title = "Magazine", imagePath = getResourceName(context, R.drawable.magazine_service_icon), position =  1, isEnabled =  true, destination = 1),
//        ServiceEntity(title = "Notifications", imagePath = getResourceName(context, R.drawable.notifications_service_icon), position =  2, isEnabled =  true, destination = 2),
        ServiceEntity(title = "Schedule", imagePath = getResourceName(context, R.drawable.schedule_service_icon), position =  3, isEnabled =  true, destination = 3),
        ServiceEntity(title = "Statistics", imagePath = getResourceName(context, R.drawable.statistics_service_icon), position =  4, isEnabled =  true, destination = 4),
//        ServiceEntity(title = "Messenger", imagePath = getResourceName(context, R.drawable.messenger_service_icon), position =  5, isEnabled =  true, destination = 5),
        ServiceEntity(title = "Sections", imagePath = getResourceName(context, R.drawable.sections_service_icon), position =  6, isEnabled =  true, destination = 6),
        ServiceEntity(title = "Session", imagePath = getResourceName(context, R.drawable.session_service_icon), position =  7, isEnabled =  true, destination = 7),
        ServiceEntity(title = "Practice", imagePath = getResourceName(context, R.drawable.practice_service_icon), position =  8, isEnabled =  true, destination = 8),
        ServiceEntity(title = "About", imagePath = getResourceName(context, R.drawable.about_service_icon), position =  9, isEnabled =  true, destination = 9),
        ServiceEntity(title = "Students", imagePath = getResourceName(context, R.drawable.students_service_icon), position =  10, isEnabled =  true, destination = 10),
//        ServiceEntity(title = "Settings", imagePath = getResourceName(context, R.drawable.settings_service_icon), position =  11, isEnabled =  true, destination = 11)
    )
}

private fun getResourceName(context: Context, resourceId: Int): String {
    return context.resources.getResourceEntryName(resourceId)
}