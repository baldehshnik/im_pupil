package com.sparkfusion.core.common.viewmodel

import androidx.lifecycle.ViewModel

abstract class DefaultViewModel<ScreenState : State, ScreenIntent : Intent> : ViewModel() {

    abstract fun initialState(): ScreenState

    abstract fun handleIntent(intent: ScreenIntent)
}