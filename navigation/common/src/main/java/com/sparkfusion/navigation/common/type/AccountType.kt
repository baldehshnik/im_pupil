package com.sparkfusion.navigation.common.type

sealed class AccountType(val type: String) {
    data object Admin : AccountType("admin")
    data object Pupil : AccountType("pupil")
    data object Common : AccountType("common")
}