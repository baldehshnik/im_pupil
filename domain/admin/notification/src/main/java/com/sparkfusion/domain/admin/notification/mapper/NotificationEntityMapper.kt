package com.sparkfusion.domain.admin.notification.mapper

import com.sparkfusion.core.common.api.parser.toLocalDateTime
import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portnotification.NotificationEntity
import com.sparkfusion.domain.admin.port.portnotification.NotificationModel
import com.sparkfusion.domain.admin.port.portnotification.NotificationTypes
import javax.inject.Inject

internal class NotificationEntityMapper @Inject constructor(
) : Mapper<NotificationEntity, NotificationModel> {

    override suspend fun map(input: NotificationEntity): NotificationModel = with(input) {
        NotificationModel(
            id,
            icon,
            title,
            description,
            dateTime.toLocalDateTime(),
            status,
            NotificationTypes.fromValue(type)
        )
    }
}