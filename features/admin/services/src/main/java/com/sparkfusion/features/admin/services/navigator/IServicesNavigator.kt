package com.sparkfusion.features.admin.services.navigator

interface IServicesNavigator {

    fun navigateToNewsScreen(id: Int)
    fun navigateToAboutApplicationScreen()

    fun navigateToStudentsService()
    fun navigateToStatisticsService()
    fun navigateToAboutService()
    fun navigateToSessionService()
    fun navigateToPracticeService()
    fun navigateToSectionsService()
    fun navigateToMagazineService()
    fun navigateToScheduleService()
}