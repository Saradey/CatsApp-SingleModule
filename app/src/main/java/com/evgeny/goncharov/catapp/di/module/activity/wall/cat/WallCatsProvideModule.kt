package com.evgeny.goncharov.catapp.di.module.activity.wall.cat

import com.evgeny.goncharov.catapp.feature.wall.cats.rest.ApiBreeds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object WallCatsProvideModule {

    @Provides
    @JvmStatic
    fun provideApiBreeds(retrofit: Retrofit): ApiBreeds = retrofit.create(ApiBreeds::class.java)
}