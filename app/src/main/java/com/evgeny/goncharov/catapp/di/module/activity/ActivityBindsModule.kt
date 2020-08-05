package com.evgeny.goncharov.catapp.di.module.activity

import android.content.Context
import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.consts.TAG_ACTIVITY_CONTEXT
import com.evgeny.goncharov.catapp.feature.splash.screen.router.SplashScreenRouter
import com.evgeny.goncharov.catapp.feature.splash.screen.router.SplashScreenRouterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface ActivityBindsModule {

    @Binds
    @Named(TAG_ACTIVITY_CONTEXT)
    fun bindActivityContext(mainActivity: MainActivity): Context

    @Binds
    fun bindScreenRouter(router: SplashScreenRouterImpl): SplashScreenRouter
}