package com.evgeny.goncharov.catapp.feature.wall.cats.di.components

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.RestWallCatsModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.WallCatsBindsModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.WallCatsProvidesModule
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsView
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModelImpl
import dagger.Subcomponent

@Subcomponent(
    modules = [
        WallCatsBindsModule::class,
        WallCatsProvidesModule::class,
        RestWallCatsModule::class
    ]
)
@FragmentScope
interface WallCatsSubcomponent {

    companion object {
        var component: WallCatsSubcomponent? = null
    }

    fun inject(view: WallCatsView)

    fun inject(viewModel: WallCatsViewModelImpl)


    @Subcomponent.Factory
    interface Factory {
        fun plus(): WallCatsSubcomponent
    }

}