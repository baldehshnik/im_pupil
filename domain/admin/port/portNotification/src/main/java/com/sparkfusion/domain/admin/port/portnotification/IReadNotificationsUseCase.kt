package com.sparkfusion.domain.admin.port.portnotification

import com.sparkfusion.core.common.result.Answer

interface IReadNotificationsUseCase {

    suspend fun readNotifications(): Answer<List<NotificationModel>>
}