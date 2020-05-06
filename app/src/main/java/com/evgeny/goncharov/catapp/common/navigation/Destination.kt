package com.evgeny.goncharov.catapp.common.navigation

sealed class Destination {

    object SplashScreen : Destination()

    object CatWallScreen : Destination()

    object CatDescription : Destination()

    object CatSearchScreen : Destination()

    object SettingsScreen : Destination()

}