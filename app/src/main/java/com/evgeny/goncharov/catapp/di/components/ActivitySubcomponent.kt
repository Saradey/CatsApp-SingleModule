package com.evgeny.goncharov.catapp.di.components

import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.di.module.ActivityBindsModule
import com.evgeny.goncharov.catapp.di.module.CreateSubFragmentComponents
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import com.evgeny.goncharov.catapp.feature.splash.screen.ui.SplashScreenFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import dagger.BindsInstance
import dagger.Subcomponent


@ActivityScope
@Subcomponent(
    modules = [
        ActivityBindsModule::class,
        CreateSubFragmentComponents::class
    ]
)
interface ActivitySubcomponent {

    fun inject(fragment: SplashScreenFragment)

    fun inject(fragment: WallCatsFragment)


    @Subcomponent.Factory
    interface Factory {
        fun plus(@BindsInstance activity: MainActivity): ActivitySubcomponent
    }

}