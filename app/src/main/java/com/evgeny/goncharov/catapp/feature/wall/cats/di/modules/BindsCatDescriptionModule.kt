package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.CatDescriptionInteractorImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.ICatDescriptionInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.CatDescriptionRepositoryImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.ICatDescriptionRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsCatDescriptionModule {

    @Binds
    @FragmentScope
    fun bindCatDescriptionInteractor(interactor: CatDescriptionInteractorImpl):
            ICatDescriptionInteractor


    @Binds
    @FragmentScope
    fun bindCatDescriptionRepository(repository: CatDescriptionRepositoryImpl):
            ICatDescriptionRepository

}