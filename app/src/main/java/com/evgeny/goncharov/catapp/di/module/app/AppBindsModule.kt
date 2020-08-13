package com.evgeny.goncharov.catapp.di.module.app

import android.content.Context
import com.evgeny.goncharov.catapp.App
import com.evgeny.goncharov.catapp.common.language.manager.LanguageManager
import com.evgeny.goncharov.catapp.common.language.manager.LanguageManagerImpl
import com.evgeny.goncharov.catapp.common.navigation.MainRouter
import com.evgeny.goncharov.catapp.common.navigation.MainRouterImpl
import com.evgeny.goncharov.catapp.common.navigation.Navigation
import com.evgeny.goncharov.catapp.common.navigation.NavigationImpl
import com.evgeny.goncharov.catapp.common.theme.manager.ThemeManager
import com.evgeny.goncharov.catapp.common.theme.manager.ThemeManagerImpl
import com.evgeny.goncharov.catapp.di.qualified.AppContext
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    @AppScope
    fun bindNavigation(navigation: NavigationImpl): Navigation

    @Binds
    @AppScope
    fun bindThemeManager(manager: ThemeManagerImpl): ThemeManager

    @Binds
    @AppContext
    fun bindAppContext(app: App): Context

    @Binds
    @AppScope
    fun bindMainRouter(routerImpl: MainRouterImpl): MainRouter

    @Binds
    @AppScope
    fun bindLanguageManager(manager: LanguageManagerImpl): LanguageManager
}