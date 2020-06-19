package com.evgeny.goncharov.catapp.feature.search.cats.gateway

import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatchedValueObject

interface ISearchCatGateway {

    suspend fun loadFromInternet(request: Map<String, String>): List<CatCatchedValueObject>

    suspend fun loadFromDatabase(text: String): List<CatCatchedValueObject>

}