package com.evgeny.goncharov.catapp.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.common.navigation.Navigation

class ActivityLifeCycle(
    private val mainActivity: MainActivity,
    private val navigationImpl: Navigation
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