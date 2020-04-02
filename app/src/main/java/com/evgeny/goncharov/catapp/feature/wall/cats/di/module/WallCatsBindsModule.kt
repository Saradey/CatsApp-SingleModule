package com.evgeny.goncharov.catapp.feature.wall.cats.di.module

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.WallCatInteractorImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.IWallCatRepository
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.WallCatRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface WallCatsBindsModule {

    @Binds
    @FragmentScope
    fun bindWallCatInteractor(interactor: WallCatInteractorImpl): IWallCatInteractor

    @Binds
    @FragmentScope
    fun bindWallCatRepository(repository: WallCatRepositoryImpl): IWallCatRepository

}