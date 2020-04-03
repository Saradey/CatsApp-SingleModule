package com.evgeny.goncharov.catapp.feature.wall.cats.di.component

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.di.module.RestModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.module.WallCatsBindsModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.module.WallCatsProvidesModule
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModelImpl
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        WallCatsBindsModule::class,
        WallCatsProvidesModule::class,
        RestModule::class
    ]
)
@FragmentScope
interface WallCatsSubcomponent {

    fun inject(view: WallCatsViewImpl)

    fun inject(viewModel: WallCatsViewModelImpl)

    @Subcomponent.Factory
    interface Factory {
        fun plus(@BindsInstance fragment: WallCatsFragment): WallCatsSubcomponent
    }

}