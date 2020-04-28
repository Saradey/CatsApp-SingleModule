package com.evgeny.goncharov.catapp.di.module.app

import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.di.scope.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named

@Module
class CommonModule {

    @Provides
    @AppScope
    fun provideHandler() = MainThreadExecutor()

}