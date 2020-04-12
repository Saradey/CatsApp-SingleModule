package com.evgeny.goncharov.catapp.di.components

import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.di.module.activity.ActivityBindsModule
import com.evgeny.goncharov.catapp.di.module.activity.ActivityProvidesModule
import com.evgeny.goncharov.catapp.di.module.activity.CreateSubFragmentComponents
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import com.evgeny.goncharov.catapp.feature.splash.screen.ui.SplashScreenFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import dagger.BindsInstance
import dagger.Subcomponent


@ActivityScope
@Subcomponent(
    modules = [
        ActivityBindsModule::class,
        CreateSubFragmentComponents::class,
        ActivityProvidesModule::class
    ]
)
interface ActivitySubcomponent {

    fun inject(fragment: SplashScreenFragment)

    fun inject(fragment: WallCatsFragment)

    fun inject(fragment: CatDescriptionFragment)


    @Subcomponent.Factory
    interface Factory {
        fun plus(
            @BindsInstance activity: MainActivity,
            @BindsInstance router: IMainRouter
        ): ActivitySubcomponent
    }

}