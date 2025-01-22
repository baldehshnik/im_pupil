package com.sparkfusion.features.admin.notifications.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.admin.port.portnotification.IReadNotificationsUseCase
import com.sparkfusion.domain.admin.port.portnotification.IUpdateNotificationStatusUseCase
import com.sparkfusion.domain.admin.port.portnotification.NotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val readAdminNotificationsUseCase: IReadNotificationsUseCase,
    private val updateNotificationStatusUseCase: IUpdateNotificationStatusUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    internal val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    fun readNotifications() {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readAdminNotificationsUseCase.readNotifications()
                .onSuccess { list ->
                    val unread = list.filter { !it.status }
                    val read = list.filter { it.status }
                    _readingState.update { ReadingState.Success(unread, read) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun updateNotificationState(id: Int) {
        if (updatingState.value == UpdatingState.Progress) {
            return
        }

        _updatingState.update { UpdatingState.Progress }
        viewModelScope.launch {
            updateNotificationStatusUseCase.updateStatus(id)
                .onSuccess {
                    val state = readingState.value
                    if (state is ReadingState.Success) {
                        val readItem = state.unread.find { it.id == id } ?: return@launch
                        val newUnreadList = state.unread.toMutableList()
                        newUnreadList.remove(readItem)

                        val newReadList = state.read.toMutableList()
                        newReadList.add(readItem)

                        _readingState.update { ReadingState.Success(newUnreadList, newReadList) }
                    }
                }
                .onFailure {
                    _updatingState.update { UpdatingState.Error }
                }
        }
    }

    fun clearUpdatingState() {
        _updatingState.update { UpdatingState.Initial }
    }

    internal sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Error : UpdatingState
        data object Progress : UpdatingState
    }

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(
            val unread: List<NotificationModel>,
            val read: List<NotificationModel>
        ) : ReadingState
    }
}


























