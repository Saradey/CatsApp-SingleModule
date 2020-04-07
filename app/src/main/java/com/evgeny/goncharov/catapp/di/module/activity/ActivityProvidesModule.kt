package com.evgeny.goncharov.catapp.di.module.activity

import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityProvidesModule {

    @Provides
    @ActivityScope
    fun provideMainRouter(router: IMainRouter) = router

}