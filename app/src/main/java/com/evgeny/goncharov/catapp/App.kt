package com.evgeny.goncharov.catapp

import android.app.Application
import com.evgeny.goncharov.catapp.di.components.AppComponent
import com.evgeny.goncharov.catapp.di.components.DaggerAppComponent

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this)
    }

}