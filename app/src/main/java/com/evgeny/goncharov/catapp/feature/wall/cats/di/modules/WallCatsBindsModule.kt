package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import androidx.lifecycle.LifecycleOwner
import com.evgeny.goncharov.catapp.consts.TAG_LIFECYCLE_WALL_CAT
import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.WallCatInteractorImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.IWallCatRepository
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.WallCatRepositoryImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface WallCatsBindsModule {

    @Binds
    @FragmentScope
    fun bindWallCatInteractor(interactor: WallCatInteractorImpl): IWallCatInteractor

    @Binds
    @FragmentScope
    fun bindWallCatRepository(repository: WallCatRepositoryImpl): IWallCatRepository

    @Binds
    @Named(TAG_LIFECYCLE_WALL_CAT)
    fun bindLifeCycleOwner(fragment: WallCatsFragment): LifecycleOwner

}