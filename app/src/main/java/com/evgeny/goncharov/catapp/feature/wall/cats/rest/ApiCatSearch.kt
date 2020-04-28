package com.evgeny.goncharov.catapp.feature.wall.cats.rest

import com.evgeny.goncharov.catapp.consts.BREEDS_SEARCH_URL
import com.evgeny.goncharov.catapp.consts.HEADERS_VALUE
import com.evgeny.goncharov.catapp.feature.wall.cats.model.response.ChooseCatBreedResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiCatSearch {

    @Headers(HEADERS_VALUE)
    @GET(BREEDS_SEARCH_URL)
    fun getCatDescriptionAsync(@QueryMap request: Map<String, String>): Deferred<List<ChooseCatBreedResponse>>

}