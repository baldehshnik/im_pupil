package com.sparkfusion.navigation.common.navigator

import com.sparkfusion.features.common.about.navigator.IAboutApplicationNavigator
import com.sparkfusion.navigation.core.navigator.INavigator

class AboutApplicationNavigator(
    private val navigator: INavigator
): IAboutApplicationNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }
}