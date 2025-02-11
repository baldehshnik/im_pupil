package com.sparkfusion.data.admin.repository.notification

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.NotificationApiService
import com.sparkfusion.dataport.admin.portnotification.IAdminNotificationRepository
import com.sparkfusion.dataport.admin.portnotification.NotificationEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AdminNotificationRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val notificationApiService: NotificationApiService
): IAdminNotificationRepository {

    override suspend fun readNotifications(): Answer<List<NotificationEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(notificationApiService.readAdminNotifications())
            .handleFetchedData()
    }

    override suspend fun updateNotification(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(notificationApiService.updateAdminNotification(id))
            .handleFetchedData()
    }
}






















