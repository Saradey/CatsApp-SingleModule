package com.evgeny.goncharov.catapp.feature.search.cats.gateway

import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched

interface SearchCatGateway {

    suspend fun loadFromInternet(request: Map<String, String>): List<CatCatched>

    suspend fun loadFromDatabase(text: String): List<CatCatched>

}