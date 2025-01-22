package com.sparkfusion.dataport.admin.portnotification

import com.sparkfusion.core.common.result.Answer

interface IAdminNotificationRepository {

    suspend fun readNotifications(): Answer<List<NotificationEntity>>

    suspend fun updateNotification(id: Int): Answer<Unit>
}