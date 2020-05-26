package com.evgeny.goncharov.catapp.di.components

import com.evgeny.goncharov.catapp.MainActivity
import com.evgeny.goncharov.catapp.di.module.activity.ActivityBindsModule
import com.evgeny.goncharov.catapp.di.module.activity.ActivityProvidesModule
import com.evgeny.goncharov.catapp.di.module.activity.CreateSubFragmentComponents
import com.evgeny.goncharov.catapp.di.module.activity.wall.cat.WallCatsBindsModule
import com.evgeny.goncharov.catapp.di.module.activity.wall.cat.WallCatsProvideModule
import com.evgeny.goncharov.catapp.di.scope.ActivityScope
import com.evgeny.goncharov.catapp.feature.search.cats.ui.SearchCatFragment
import com.evgeny.goncharov.catapp.feature.settings.ui.SettingsFragment
import com.evgeny.goncharov.catapp.feature.splash.screen.ui.SplashScreenFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsView
import dagger.BindsInstance
import dagger.Subcomponent


@ActivityScope
@Subcomponent(
    modules = [
        ActivityBindsModule::class,
        CreateSubFragmentComponents::class,
        ActivityProvidesModule::class,
        WallCatsProvideModule::class,
        WallCatsBindsModule::class
    ]
)
interface ActivitySubcomponent {

    companion object {
        lateinit var component: ActivitySubcomponent
    }

    fun inject(fragment: SplashScreenFragment)

    fun inject(fragment: WallCatsFragment)

    fun inject(fragment: CatDescriptionFragment)

    fun inject(fragment: SearchCatFragment)

    fun inject(fragment: SettingsFragment)

    fun inject(view: WallCatsView)

    @Subcomponent.Factory
    interface Factory {
        fun plus(
            @BindsInstance activity: MainActivity
        ): ActivitySubcomponent
    }

}