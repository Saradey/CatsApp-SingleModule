package com.evgeny.goncharov.catapp.di.module.app

import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
object CommonModule {

    @Provides
    @AppScope
    @JvmStatic
    fun provideHandler() = MainThreadExecutor()

}