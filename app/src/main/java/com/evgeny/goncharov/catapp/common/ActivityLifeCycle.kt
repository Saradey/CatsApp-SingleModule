package com.evgeny.goncharov.catapp.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.common.navigation.INavigation
import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager

class ActivityLifeCycle(
    private val mainActivity: MainActivity,
    private val navigationImpl: INavigation
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        navigationImpl.attachActivity(mainActivity)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        navigationImpl.detachActivity()
    }
}