package com.evgeny.goncharov.catapp.feature.search.cats.di.modules

import com.evgeny.goncharov.catapp.feature.search.cats.interactor.SearchCatInteractor
import com.evgeny.goncharov.catapp.feature.search.cats.interactor.SearchCatInteractorImpl
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.SearchCatGateway
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.SearchCatGatewayImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchCatBindsModule {

    @Binds
    fun bindSearchCatInteractor(interactor: SearchCatInteractorImpl): SearchCatInteractor

    @Binds
    fun bindSearchCatRepository(repository: SearchCatGatewayImpl): SearchCatGateway


}