package com.evgeny.goncharov.catapp.feature.search.cats.di.modules

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.SearchCatGateway
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.SearchCatGatewayImpl
import com.evgeny.goncharov.catapp.feature.search.cats.interactor.SearchCatInteractor
import com.evgeny.goncharov.catapp.feature.search.cats.interactor.SearchCatInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchCatBindsModule {

    @Binds
    @FragmentScope
    fun bindSearchCatInteractor(interactor: SearchCatInteractorImpl): SearchCatInteractor

    @Binds
    @FragmentScope
    fun bindSearchCatRepository(repository: SearchCatGatewayImpl): SearchCatGateway
}