package com.evgeny.goncharov.catapp.feature.search.cats.di.modules

import com.evgeny.goncharov.catapp.feature.search.cats.interactor.ISearchCatInteractor
import com.evgeny.goncharov.catapp.feature.search.cats.interactor.SearchCatInteractorImpl
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.ISearchCatGateway
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.SearchCatGatewayImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchCatBindsModule {

    @Binds
    fun bindSearchCatInteractor(interactor: SearchCatInteractorImpl): ISearchCatInteractor

    @Binds
    fun bindSearchCatRepository(repository: SearchCatGatewayImpl): ISearchCatGateway


}