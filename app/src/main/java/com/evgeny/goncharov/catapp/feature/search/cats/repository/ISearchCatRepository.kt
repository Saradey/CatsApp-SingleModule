package com.evgeny.goncharov.catapp.feature.search.cats.repository

import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched

interface ISearchCatRepository {

    suspend fun loadFromInternet(request: Map<String, String>): List<CatCatched>

    suspend fun loadFromDatabase(text: String): List<CatCatched>

}