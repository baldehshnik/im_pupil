package com.sparkfusion.data.admin.source

import com.sparkfusion.dataport.admin.portnotification.NotificationEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface NotificationApiService {

    @GET("/notifications/admin")
    suspend fun readAdminNotifications(): Response<List<NotificationEntity>>

    @POST("/notifications/admin/update")
    suspend fun updateAdminNotification(@Query("notificationId") notificationId: Int): Response<Unit>
}




























