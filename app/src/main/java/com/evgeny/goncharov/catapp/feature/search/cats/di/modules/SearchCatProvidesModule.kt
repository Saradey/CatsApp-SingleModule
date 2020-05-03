package com.evgeny.goncharov.catapp.feature.search.cats.di.modules

import com.evgeny.goncharov.catapp.di.scope.FragmentScope
import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiCatSearch
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchCatProvidesModule {

    @Provides
    @FragmentScope
    fun provideApiSearchCatFromName(retrofit: Retrofit):
            ApiCatSearch = retrofit.create(ApiCatSearch::class.java)

}