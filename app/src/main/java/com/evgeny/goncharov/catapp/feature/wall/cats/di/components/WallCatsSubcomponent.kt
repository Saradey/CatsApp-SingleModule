package com.evgeny.goncharov.catapp.feature.wall.cats.di.components

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.RestWallCatsModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.WallCatsBindsModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.WallCatsProvidesModule
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.WallCatsViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModelImpl
import dagger.BindsInstance
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

    fun inject(view: WallCatsViewImpl)

    fun inject(viewModel: WallCatsViewModelImpl)

    fun inject(adapter: PageKeyedDataSourceCatBreeds)


    @Subcomponent.Factory
    interface Factory {
        fun plus(@BindsInstance fragment: WallCatsFragment): WallCatsSubcomponent
    }

}