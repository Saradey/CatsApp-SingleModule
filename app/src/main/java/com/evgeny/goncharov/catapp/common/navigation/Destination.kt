package com.evgeny.goncharov.catapp.common.navigation

sealed class Destination {

    object SplashScreen : Destination()

    object CatWallScreen : Destination()

    object CatDescription : Destination()

}