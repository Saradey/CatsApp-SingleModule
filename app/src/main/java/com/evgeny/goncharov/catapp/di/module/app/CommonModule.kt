package com.evgeny.goncharov.catapp.di.module.app

import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    @AppScope
    fun provideHandler() = MainThreadExecutor()

}