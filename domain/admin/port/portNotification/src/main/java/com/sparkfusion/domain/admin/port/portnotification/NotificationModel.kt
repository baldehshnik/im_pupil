package com.sparkfusion.domain.admin.port.portnotification

import java.time.LocalDateTime

data class NotificationModel(
    val id: Int,
    val icon: String,
    val title: String,
    val description: String,
    val dateTime: LocalDateTime,
    val status: Boolean,
    val type: NotificationTypes
)
