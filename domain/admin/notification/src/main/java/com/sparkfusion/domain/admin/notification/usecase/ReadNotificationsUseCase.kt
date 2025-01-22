package com.sparkfusion.domain.admin.notification.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portnotification.IAdminNotificationRepository
import com.sparkfusion.domain.admin.notification.mapper.NotificationEntityMapper
import com.sparkfusion.domain.admin.port.portnotification.IReadNotificationsUseCase
import com.sparkfusion.domain.admin.port.portnotification.NotificationModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadNotificationsUseCase @Inject constructor(
    private val adminNotificationRepository: IAdminNotificationRepository,
    private val notificationEntityMapper: NotificationEntityMapper
): IReadNotificationsUseCase {

    override suspend fun readNotifications(): Answer<List<NotificationModel>> {
        return adminNotificationRepository.readNotifications()
            .suspendMap { it.map { model -> notificationEntityMapper.map(model) } }
    }
}

















