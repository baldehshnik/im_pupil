package com.sparkfusion.navigation.admin.navigator

import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.navigation.adminservicesport.about.AboutDestination
import com.sparkfusion.navigation.adminservicesport.statistics.StatisticsFacultiesDestination
import com.sparkfusion.navigation.adminservicesport.students.StudentsFacultyDestination
import com.sparkfusion.navigation.commoncoreport.destination.AboutApplicationDestination
import com.sparkfusion.navigation.commoncoreport.destination.NewsDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class ServicesNavigator(private val navigator: INavigator) : IServicesNavigator {

    override fun navigateToNewsScreen() {
        navigator.navigateTo(NewsDestination)
    }

    override fun navigateToAboutApplicationScreen() {
        navigator.navigateTo(AboutApplicationDestination)
    }

    override fun navigateToStudentsService() {
        navigator.navigateTo(StudentsFacultyDestination)
    }

    override fun navigateToStatisticsService() {
        navigator.navigateTo(StatisticsFacultiesDestination)
    }

    override fun navigateToAboutService() {
        navigator.navigateTo(AboutDestination)
    }
}