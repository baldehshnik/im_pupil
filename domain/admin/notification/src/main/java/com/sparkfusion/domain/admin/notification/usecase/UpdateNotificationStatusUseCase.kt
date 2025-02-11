package com.sparkfusion.domain.admin.notification.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portnotification.IAdminNotificationRepository
import com.sparkfusion.domain.admin.port.portnotification.IUpdateNotificationStatusUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateNotificationStatusUseCase @Inject constructor(
    private val adminNotificationRepository: IAdminNotificationRepository
): IUpdateNotificationStatusUseCase {

    override suspend fun updateStatus(id: Int): Answer<Unit> {
        return adminNotificationRepository.updateNotification(id)
    }
}