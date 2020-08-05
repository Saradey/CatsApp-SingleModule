package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.CatDescriptionInteractorImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.CatDescriptionInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.gateway.CatDescriptionGatewayImpl
import com.evgeny.goncharov.catapp.feature.wall.cats.gateway.CatDescriptionGateway
import dagger.Binds
import dagger.Module

@Module
interface BindsCatDescriptionModule {

    @Binds
    @FragmentScope
    fun bindCatDescriptionInteractor(interactor: CatDescriptionInteractorImpl):
            CatDescriptionInteractor


    @Binds
    @FragmentScope
    fun bindCatDescriptionRepository(repository: CatDescriptionGatewayImpl):
            CatDescriptionGateway

}