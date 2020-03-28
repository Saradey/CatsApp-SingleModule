package com.evgeny.goncharov.catapp.common.navigation

import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.common.navigation.INavigation
import javax.inject.Inject

class NavigationImpl @Inject constructor() :
    INavigation {

    private var activity: MainActivity? = null


    override fun attachActivity(mainActivity: MainActivity) {
        activity = mainActivity
    }

    override fun detachActivity() {
        activity = null
    }

}