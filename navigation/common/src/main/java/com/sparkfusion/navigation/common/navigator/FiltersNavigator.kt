package com.sparkfusion.navigation.common.navigator

import com.sparkfusion.features.common.filters.navigator.IFiltersNavigator
import com.sparkfusion.navigation.commoncoreport.destination.FiltersDestination
import com.sparkfusion.navigation.core.navigator.INavigator

class FiltersNavigator(private val navigator: INavigator): IFiltersNavigator {

    override fun popBackStack() {
        navigator.popBackStack()
    }

    override fun navigateToHomeScreen() {
        navigator.popBackStackInclusive(FiltersDestination)
    }
}