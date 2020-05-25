package com.evgeny.goncharov.catapp.feature.search.cats.di

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.search.cats.di.modules.SearchCatBindsModule
import com.evgeny.goncharov.catapp.feature.search.cats.di.modules.SearchCatProvidesModule
import com.evgeny.goncharov.catapp.feature.search.cats.ui.view.SearchCatViewImpl
import com.evgeny.goncharov.catapp.feature.search.cats.view.model.SearchCatViewModelImpl
import dagger.Subcomponent


@FragmentScope
@Subcomponent(
    modules = [
        SearchCatProvidesModule::class,
        SearchCatBindsModule::class
    ]
)
interface SearchCatSubcomponent {

    companion object {
        var component : SearchCatSubcomponent? = null
    }


    fun inject(viewModel: SearchCatViewModelImpl)

    fun inject(view: SearchCatViewImpl)

    @Subcomponent.Factory
    interface Factory {
        fun plus(): SearchCatSubcomponent
    }


}