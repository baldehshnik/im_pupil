package com.sparkfusion.navigation.pupil.host

import com.sparkfusion.navigation.pupilcoreport.PupilAccountDestination
import com.sparkfusion.navigation.pupilcoreport.PupilHomeDestination
import com.sparkfusion.navigation.pupilcoreport.PupilServicesDestination

fun getPupilBottomBarItemRoutes(): List<String> {
    return listOf(
        PupilHomeDestination.route,
        PupilAccountDestination.route,
        PupilServicesDestination.route
    )
}