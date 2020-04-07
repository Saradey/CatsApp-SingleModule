package com.evgeny.goncharov.catapp.feature.wall.cats.rest

import com.evgeny.goncharov.catapp.consts.HEADERS_VALUE
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedImageResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiBreeds {

    @Headers(HEADERS_VALUE)
    @GET("v1/breeds")
    fun getBreedsAsync(@QueryMap request: Map<String, Int>): Deferred<List<CatBreedModelResponse>>

    @Headers(HEADERS_VALUE)
    @GET("v1/images/search")
    fun getImageUrlAsync(@QueryMap request: Map<String, String>): Deferred<List<CatBreedImageResponse>>

}