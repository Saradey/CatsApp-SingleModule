package com.evgeny.goncharov.catapp.di.module.activity.wall.cat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evgeny.goncharov.catapp.common.ViewModelFactory
import com.evgeny.goncharov.catapp.di.ViewModelKey
import com.evgeny.goncharov.catapp.feature.wall.cats.gateway.WallCatGateway
import com.evgeny.goncharov.catapp.feature.wall.cats.gateway.WallCatGatewayImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.WallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.WallCatInteractorImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.view.model.WallCatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WallCatsBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WallCatsViewModel::class)
    fun bindWallCatsViewModel(vm: WallCatsViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindWallCatInteractor(interactor: WallCatInteractorImpl): WallCatInteractor

    @Binds
    fun bindWallCatRepository(repository: WallCatGatewayImpl): WallCatGateway
}