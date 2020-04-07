package com.evgeny.goncharov.catapp.common.navigation

import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.feature.splash.screen.ui.SplashScreenFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import javax.inject.Inject

class NavigationImpl @Inject constructor() : INavigation {

    private var activity: MainActivity? = null

    override fun attachActivity(mainActivity: MainActivity) {
        activity = mainActivity
    }


    override fun detachActivity() {
        activity = null
    }


    override fun goTo(destination: Destination) {
        activity?.let {
            when (destination) {
                Destination.SplashScreen -> goToSplashScreenScreen()
                Destination.CatWallScreen -> goToCatWallScreen()
            }
        }
    }


    private fun goToSplashScreenScreen() {
        val fragment = SplashScreenFragment.getInstance()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frmRootField, fragment)
            ?.commit()
    }


    private fun goToCatWallScreen() {
        val fragment = WallCatsFragment.getInstance()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frmRootField, fragment)
            ?.commit()
    }


    override fun getNowMatchFromStack(): Int {
        return activity?.supportFragmentManager?.backStackEntryCount ?: 1
    }


    override fun appFinish() {
        activity?.finish()
    }


    override fun popStack() {
        activity?.supportFragmentManager?.popBackStack()
    }
}