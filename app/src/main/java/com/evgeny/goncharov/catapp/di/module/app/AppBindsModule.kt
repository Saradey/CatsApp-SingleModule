package com.evgeny.goncharov.catapp.di.module.app

import android.content.Context
import com.evgeny.goncharov.catapp.App
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.common.navigation.INavigation
import com.evgeny.goncharov.catapp.common.navigation.MainRouterImpl
import com.evgeny.goncharov.catapp.common.navigation.NavigationImpl
import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.common.theme.manager.ThemeManagerImpl
import com.evgeny.goncharov.catapp.consts.TAG_APPLICATION_CONTEXT
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface AppBindsModule {

    @Binds
    @AppScope
    fun bindNavigation(navigation: NavigationImpl): INavigation

    @Binds
    @AppScope
    fun bindThemeManager(manager: ThemeManagerImpl): IThemeManager

    @Binds
    @Named(TAG_APPLICATION_CONTEXT)
    fun bindAppContext(app: App): Context

    @Binds
    @AppScope
    fun bindMainRouter(routerImpl: MainRouterImpl): IMainRouter

}