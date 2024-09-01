package com.sparkfusion.data.base.db.initializer

import android.content.Context
import com.sparkfusion.core.resource.R
import com.sparkfusion.data.base.db.entity.ServiceEntity

fun getInitializeServicesList(context: Context): List<ServiceEntity> {
    return listOf(
        ServiceEntity(title = "Magazine", imagePath = getResourceName(context, R.drawable.magazine_service_icon), position =  1, isEnabled =  true),
        ServiceEntity(title = "Notifications", imagePath = getResourceName(context, R.drawable.notifications_service_icon), position =  2, isEnabled =  true),
        ServiceEntity(title = "Schedule", imagePath = getResourceName(context, R.drawable.schedule_service_icon), position =  3, isEnabled =  true),
        ServiceEntity(title = "Statistics", imagePath = getResourceName(context, R.drawable.statistics_service_icon), position =  4, isEnabled =  true),
        ServiceEntity(title = "Messenger", imagePath = getResourceName(context, R.drawable.messenger_service_icon), position =  5, isEnabled =  true),
        ServiceEntity(title = "Sections", imagePath = getResourceName(context, R.drawable.sections_service_icon), position =  6, isEnabled =  true),
        ServiceEntity(title = "Session", imagePath = getResourceName(context, R.drawable.session_service_icon), position =  7, isEnabled =  true),
        ServiceEntity(title = "Practice", imagePath = getResourceName(context, R.drawable.practice_service_icon), position =  8, isEnabled =  true),
        ServiceEntity(title = "About", imagePath = getResourceName(context, R.drawable.about_service_icon), position =  9, isEnabled =  true),
        ServiceEntity(title = "Students", imagePath = getResourceName(context, R.drawable.students_service_icon), position =  10, isEnabled =  true),
        ServiceEntity(title = "Settings", imagePath = getResourceName(context, R.drawable.settings_service_icon), position =  11, isEnabled =  true)
    )
}

private fun getResourceName(context: Context, resourceId: Int): String {
    return context.resources.getResourceEntryName(resourceId)
}