package com.evgeny.goncharov.catapp.di.module.app

import com.evgeny.goncharov.catapp.common.MainThreadExecutor
import com.evgeny.goncharov.catapp.consts.TAG_IO_SCOPE_GET_FROM_APP
import com.evgeny.goncharov.catapp.consts.TAG_MAIN_SCOPE_GET_FROM_APP
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
    @Named(TAG_MAIN_SCOPE_GET_FROM_APP)
    fun provideMainScope() = CoroutineScope(Dispatchers.Main + SupervisorJob())

    @Provides
    @Named(TAG_IO_SCOPE_GET_FROM_APP)
    fun provideIoScope() = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @Provides
    @AppScope
    fun provideHandler() = MainThreadExecutor()

}