package com.sparkfusion.features.common.sign_in.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.viewmodel.DefaultViewModel
import com.sparkfusion.core.common.viewmodel.Intent
import com.sparkfusion.core.common.viewmodel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : DefaultViewModel<SignInViewModel.S, SignInViewModel.I>() {

    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val showPassword = mutableStateOf(false)
    val saveLogin = mutableStateOf(false)

    private val _pages = MutableStateFlow<List<String>>(emptyList())
    val pages: StateFlow<List<String>> = _pages

    private val _isDataLoadingCompleted = MutableStateFlow(false)
    val isDataLoadingCompleted: StateFlow<Boolean> = _isDataLoadingCompleted

    val pagesCount = _pages.map { it.size }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        0
    )

    init {
        viewModelScope.launch {
            _pages.value = listOf("Image 16546546456", "Image 2", "Image 6546543")
            _isDataLoadingCompleted.value = true
        }
    }

    class S : State
    class I : Intent

    override fun initialState(): S {
        TODO("Not yet implemented")
    }

    override fun handleIntent(intent: I) {
        TODO("Not yet implemented")
    }
}
