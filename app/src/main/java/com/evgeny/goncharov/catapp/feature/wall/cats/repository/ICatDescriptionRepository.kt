package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel

interface ICatDescriptionRepository {

    suspend fun loadChooseCatFromInternet(catId: String): CatDescriptionModel?

    suspend fun loadChooseCatFromDatabase(catId: String): CatDescriptionModel?

}