package com.evgeny.goncharov.catapp.common.navigation

import android.os.Bundle
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.R
import com.evgeny.goncharov.catapp.base.BaseFragment
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
            goToReplace(
                getFragment(destination)
            )
        }
    }


    private fun goToReplace(fragment: BaseFragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frmRootField, fragment)
            ?.commit()
    }


    private fun getFragment(destination: Destination, bundle: Bundle? = null): BaseFragment {
        return when (destination) {
            Destination.SplashScreen -> SplashScreenFragment.getInstance()
            Destination.CatWallScreen -> WallCatsFragment.getInstance()
        }
    }


}