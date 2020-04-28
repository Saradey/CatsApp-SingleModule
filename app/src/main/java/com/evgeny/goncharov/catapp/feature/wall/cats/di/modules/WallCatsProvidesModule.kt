package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import com.evgeny.goncharov.catapp.feature.wall.cats.ui.adapters.PageKeyedDataSourceCatBreeds
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.IWallCatsViewModel
import dagger.Module
import dagger.Provides

@Module
class WallCatsProvidesModule {

    @Provides
    fun providePageKeyedDataSourceCatBreeds(
        viewModel: IWallCatsViewModel
    ) = PageKeyedDataSourceCatBreeds(viewModel)

}