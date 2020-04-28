package com.evgeny.goncharov.catapp.feature.wall.cats.di.modules

import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiBreeds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RestWallCatsModule {

    @Provides
    fun provideApiBreeds(retrofit: Retrofit): ApiBreeds = retrofit.create(ApiBreeds::class.java)


}