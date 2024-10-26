package com.sparkfusion.core.design.screen.faculty

import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.viewmodel.CommonViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultyViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : CommonViewModel<FacultyContract.FacultyState, FacultyContract.FacultyIntent>() {

    override fun initialState(): StateFlow<FacultyContract.FacultyState> {
        TODO("Not yet implemented")
    }

    override fun handleIntent(intent: FacultyContract.FacultyIntent) {
        when (intent) {
            FacultyContract.FacultyIntent.LoadFaculties -> loadFaculties()
        }
    }

    private val state = MutableStateFlow(FacultyContract.FacultyState())

    private fun loadFaculties() {
        viewModelScope.launch(ioDispatcher) {

        }
    }
}
