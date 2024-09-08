package com.sparkfusion.features.admin.home.viewmodel

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.viewmodel.DefaultViewModel
import com.sparkfusion.features.admin.home.entity.PostEntity
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : DefaultViewModel() {

    val floatingButtonScale = mutableFloatStateOf(1f)
    val isDataLoadingCompleted = mutableStateOf(false)
    val posts = MutableStateFlow<List<PostEntity>>(emptyList())

    init {
        viewModelScope.launch {
            delay(2000)
            posts.value = loadPosts()
            isDataLoadingCompleted.value = true
        }
    }

    fun onFabClick(navigator: IHomeNavigator, coroutineScope: CoroutineScope) {
        navigator.navigateToPostAddingScreen()
    }

    private fun loadPosts(): List<PostEntity> {
        return listOf()
    }
}