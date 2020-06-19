package com.evgeny.goncharov.catapp.feature.wall.cats.gateway

import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionDTO

interface ICatDescriptionGateway {

    suspend fun loadChooseCatFromInternet(catId: String): CatDescriptionDTO?

    suspend fun loadChooseCatFromDatabase(catId: String): CatDescriptionDTO?

    suspend fun loadChooseCatFromDatabaseSpare(catId: String): CatDescriptionDTO?

}