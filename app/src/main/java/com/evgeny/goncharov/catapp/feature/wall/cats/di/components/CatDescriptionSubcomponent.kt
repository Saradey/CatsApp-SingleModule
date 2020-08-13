package com.evgeny.goncharov.catapp.feature.wall.cats.di.components

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.BindsCatDescriptionModule
import com.evgeny.goncharov.catapp.feature.wall.cats.di.modules.RestCatDescriptionModule
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.CatDescriptionViewModelImpl
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        BindsCatDescriptionModule::class,
        RestCatDescriptionModule::class
    ]
)
interface CatDescriptionSubcomponent {

    companion object {
        var component: CatDescriptionSubcomponent? = null
    }

    fun inject(viewModel: CatDescriptionViewModelImpl)

    @Subcomponent.Factory
    interface Factory {

        fun plus(): CatDescriptionSubcomponent
    }
}