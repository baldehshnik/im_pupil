package com.sparkfusion.domain.admin.notification.di

import com.sparkfusion.domain.admin.notification.usecase.ReadNotificationsUseCase
import com.sparkfusion.domain.admin.notification.usecase.UpdateNotificationStatusUseCase
import com.sparkfusion.domain.admin.port.portnotification.IReadNotificationsUseCase
import com.sparkfusion.domain.admin.port.portnotification.IUpdateNotificationStatusUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindReadNotificationUseCaseToIReadNotificationUseCase(readNotificationsUseCase: ReadNotificationsUseCase): IReadNotificationsUseCase

    @Binds
    fun bindUpdateNotificationStatusUseCaseToIUpdateNotificationStatusUseCase(updateNotificationStatusUseCase: UpdateNotificationStatusUseCase): IUpdateNotificationStatusUseCase
}























