package com.sparkfusion.navigation.pupil

import androidx.compose.foundation.layout.Box
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sparkfusion.core.image_crop.common.CROPPED_KEY
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.features.pupil.account.screen.AccountScreenEnter
import com.sparkfusion.features.pupil.eventdetails.destination.EventDetailsScreenDestination
import com.sparkfusion.features.pupil.eventdetails.screen.EventDetailsScreenEnter
import com.sparkfusion.features.pupil.home.screen.HomeScreenEnter
import com.sparkfusion.features.pupil.services.screen.ServicesScreenEnter
import com.sparkfusion.features.pupil.sign_up.screen.SignUpScreenEnter
import com.sparkfusion.navigation.commoncoreport.destination.AboutApplicationDestination
import com.sparkfusion.navigation.commoncoreport.destination.CircleImageCropDestination
import com.sparkfusion.navigation.commoncoreport.destination.NewsDestination
import com.sparkfusion.navigation.core.keys.EVENT_ID_KEY
import com.sparkfusion.navigation.core.keys.NEWS_ID_KEY
import com.sparkfusion.navigation.pupilcoreport.PupilAccountDestination
import com.sparkfusion.navigation.pupilcoreport.PupilHomeDestination
import com.sparkfusion.navigation.pupilcoreport.PupilServicesDestination
import com.sparkfusion.navigation.pupilcoreport.PupilSignUpDestination
import com.sparkfusion.navigation.pupilservicesport.about.AboutDestination
import com.sparkfusion.navigation.pupilservicesport.magazine.MagazineDestination
import com.sparkfusion.navigation.pupilservicesport.practice.PracticeDestination
import com.sparkfusion.navigation.pupilservicesport.schedule.ScheduleDestination
import com.sparkfusion.navigation.pupilservicesport.section.SectionDestination
import com.sparkfusion.navigation.pupilservicesport.session.SessionDestination
import com.sparkfusion.navigation.pupilservicesport.statistics.StatisticsDestination
import com.sparkfusion.navigation.pupilservicesport.students.StudentsDestination

fun NavGraphBuilder.pupilSignUp(navController: NavController) {
    composable(PupilSignUpDestination.route) {
        SignUpScreenEnter(
            onBackStackClick = navController::popBackStack,
            onNavigateToSignInScreen = navController::popBackStack
        )
    }
}

fun NavGraphBuilder.pupilHome(navController: NavController) {
    composable(PupilHomeDestination.route) {
        Box {
            HomeScreenEnter(
                onNavigateToPostViewingScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(EVENT_ID_KEY, it)
                    navController.navigate(EventDetailsScreenDestination.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.pupilAccount(navController: NavController) {
    composable(PupilAccountDestination.route) {
        AccountScreenEnter(
            onNavigateToImageCroppingScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set(IMAGE_CROP_KEY, it)
                navController.navigate(CircleImageCropDestination.route)
            },
            getCroppedBitmap = {
                navController.currentBackStackEntry?.savedStateHandle?.get(CROPPED_KEY)
            }
        )
    }
}

fun NavGraphBuilder.pupilServices(navController: NavController) {
    composable(PupilServicesDestination.route) {
        ServicesScreenEnter(
            onNewsDetailsScreenNavigate = {
                navController.currentBackStackEntry?.savedStateHandle?.set(NEWS_ID_KEY, it)
                navController.navigate(NewsDestination.route)
            },
            onAboutScreenNavigate = {
                navController.navigate(AboutApplicationDestination.route)
            },
            onNavigateToMagazineService = {
                navController.navigate(MagazineDestination.route)
            },
            onNavigateToScheduleService = {
                navController.navigate(ScheduleDestination.route)
            },
            onNavigateToStatisticsService = {
                navController.navigate(StatisticsDestination.route)
            },
            onNavigateToSectionsService = {
                navController.navigate(SectionDestination.route)
            },
            onNavigateToSessionService = {
                navController.navigate(SessionDestination.route)
            },
            onNavigateToPracticeService = {
                navController.navigate(PracticeDestination.route)
            },
            onNavigateToAboutService = {
                navController.navigate(AboutDestination.route)
            },
            onNavigateToStudentsService = {
                navController.navigate(StudentsDestination.route)
            }
        )
    }
}

fun NavGraphBuilder.pupilEventDetails(navController: NavController) {
    composable(EventDetailsScreenDestination.route) {
        val eventId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(EVENT_ID_KEY)
        if (eventId != null) {
            EventDetailsScreenEnter(
                id = eventId,
                onBackStackClick = navController::popBackStack
            )
        }
    }
}

































