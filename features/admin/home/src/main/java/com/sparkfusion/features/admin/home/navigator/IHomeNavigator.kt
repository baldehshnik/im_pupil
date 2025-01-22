package com.sparkfusion.features.admin.home.navigator

interface IHomeNavigator {
    fun navigateToNotificationsScreen()
    fun navigateToPostAddingScreen()
    fun navigateToPostViewingScreen(id: Int)
    fun navigateToFiltersScreen()
}