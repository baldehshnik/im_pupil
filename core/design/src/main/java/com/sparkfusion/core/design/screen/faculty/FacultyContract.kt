package com.sparkfusion.core.design.screen.faculty

import com.sparkfusion.core.common.viewmodel.Intent
import com.sparkfusion.core.common.viewmodel.State

interface FacultyContract {

    sealed interface FacultyIntent : Intent {
        data object LoadFaculties : FacultyIntent
    }

    data class FacultyState(
        val temp: String = ""
    ) : State
}