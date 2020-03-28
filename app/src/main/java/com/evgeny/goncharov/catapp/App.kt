package com.evgeny.goncharov.catapp

import android.app.Application
import com.evgeny.goncharov.catapp.di.components.AppComponent
import com.evgeny.goncharov.catapp.di.components.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory()
            .plus(this)
    }

}
