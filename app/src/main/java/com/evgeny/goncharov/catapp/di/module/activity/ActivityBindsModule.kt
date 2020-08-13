package com.evgeny.goncharov.catapp.di.module.activity

import android.content.Context
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.di.qualified.ActivityContext
import com.evgeny.goncharov.catapp.feature.splash.screen.router.SplashScreenRouter
import com.evgeny.goncharov.catapp.feature.splash.screen.router.SplashScreenRouterImpl
import dagger.Binds
import dagger.Module

@Module
interface ActivityBindsModule {

    @Binds
    @ActivityContext
    fun bindActivityContext(mainActivity: MainActivity): Context

    @Binds
    fun bindScreenRouter(router: SplashScreenRouterImpl): SplashScreenRouter
}