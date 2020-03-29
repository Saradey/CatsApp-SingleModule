package com.evgeny.goncharov.catapp.feature.splash.screen.router

import com.evgeny.goncharov.catapp.common.navigation.Destination
import com.evgeny.goncharov.catapp.common.navigation.INavigation
import javax.inject.Inject

class SplashScreenRouterImpl @Inject constructor(
    private val navigation: INavigation
) : ISplashScreenRouter {


    override fun gotoTheWallCatFragment() {
        navigation.goTo(Destination.CatWallScreen)
    }


}