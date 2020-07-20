package com.evgeny.goncharov.catapp.feature.wall.cats.gateway

import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescription

interface ICatDescriptionGateway {

    suspend fun loadChooseCatFromInternet(catId: String): CatDescription?

    suspend fun loadChooseCatFromDatabase(catId: String): CatDescription?

    suspend fun loadChooseCatFromDatabaseSpare(catId: String): CatDescription?

}