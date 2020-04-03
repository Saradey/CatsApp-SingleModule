package com.evgeny.goncharov.catapp.feature.wall.cats.rest

import com.evgeny.goncharov.catapp.consts.API_KEY
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiBreeds {

    @Headers("x-api-key: $API_KEY")
    @GET("v1/breeds")
    fun getBreedsAsync(@QueryMap request: Map<String, Int>): Deferred<List<CatBreedModelResponse>>

}