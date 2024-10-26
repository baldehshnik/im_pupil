package com.sparkfusion.core.common.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class CommonViewModel<ScreenState : State, ScreenIntent : Intent> : ViewModel() {

    abstract fun initialState(): StateFlow<ScreenState>

    abstract fun handleIntent(intent: ScreenIntent)
}