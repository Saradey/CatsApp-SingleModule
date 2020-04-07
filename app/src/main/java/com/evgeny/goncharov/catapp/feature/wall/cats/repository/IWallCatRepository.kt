package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

interface IWallCatRepository {

    suspend fun loadWallCatFromInternet(request: WallCatRequest): List<CatBreedModel>

    suspend fun loadWallCatFromDatabase(): List<CatBreedModel>

}