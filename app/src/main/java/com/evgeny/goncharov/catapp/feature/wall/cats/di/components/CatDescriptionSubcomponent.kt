package com.evgeny.goncharov.catapp.feature.wall.cats.di.components

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.BindsCatDescriptionModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.ProvidesCatDescriptionModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.RestCatDescriptionModule
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.CatDescriptionFragment
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.view.CatDescriptionViewImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.CatDescriptionViewModelImpl
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        BindsCatDescriptionModule::class,
        ProvidesCatDescriptionModule::class,
        RestCatDescriptionModule::class
    ]
)
interface CatDescriptionSubcomponent {

    fun inject(catDescriptionViewImpl: CatDescriptionViewImpl)

    fun inject(viewModel: CatDescriptionViewModelImpl)


    @Subcomponent.Factory
    interface Factory {

        fun plus(): CatDescriptionSubcomponent

    }

}