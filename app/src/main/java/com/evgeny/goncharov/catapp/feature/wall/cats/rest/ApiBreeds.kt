package com.evgeny.goncharov.catapp.feature.wall.cats.rest

import com.evgeny.goncharov.catapp.consts.BREEDS_URL
import com.evgeny.goncharov.catapp.consts.HEADERS_VALUE
import com.evgeny.goncharov.catapp.consts.IMAGE_SEARCH_URL
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedImageResponse
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.CatBreedModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiBreeds {

    @Headers(HEADERS_VALUE)
    @GET(BREEDS_URL)
    fun getBreedsAsync(@QueryMap request: Map<String, Int>): Deferred<List<CatBreedModelResponse>>

    @Headers(HEADERS_VALUE)
    @GET(IMAGE_SEARCH_URL)
    fun getImageUrlAsync(@QueryMap request: Map<String, String>): Deferred<List<CatBreedImageResponse>>

}