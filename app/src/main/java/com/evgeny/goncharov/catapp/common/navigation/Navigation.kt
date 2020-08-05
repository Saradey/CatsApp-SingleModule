package com.evgeny.goncharov.catapp.common.navigation

import android.os.Bundle
import com.evgeny.goncharov.catapp.MainActivity

interface Navigation {

    fun attachActivity(mainActivity: MainActivity)

    fun detachActivity()

    fun goTo(destination: Destination)

    fun goTo(destination: Destination, bundle: Bundle)

    fun getNowMatchFromStack(): Int

    fun appFinish()

    fun popStack()

    fun showBackPressedWarning()


}