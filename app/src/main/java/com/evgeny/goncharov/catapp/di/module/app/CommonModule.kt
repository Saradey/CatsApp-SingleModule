package com.evgeny.goncharov.catapp.di.module.app

import com.evgeny.goncharov.catapp.consts.TAG_MAIN_SCOPE_GET_FROM_APP
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class CommonModule {

    @Provides
    @Named(TAG_MAIN_SCOPE_GET_FROM_APP)
    fun provideMainScope() = CoroutineScope(Dispatchers.Main)

}