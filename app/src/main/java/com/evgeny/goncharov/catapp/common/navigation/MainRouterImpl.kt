package com.evgeny.goncharov.catapp.common.navigation

import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val navigation: INavigation
) : IMainRouter {


    override fun showSlashScreen() {
        navigation.goTo(Destination.SplashScreen)
    }


}