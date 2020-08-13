package com.evgeny.goncharov.catapp.feature.wall.cats.gateway

import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView

interface WallCatGateway {

    suspend fun loadWallCatFromInternet(request: WallCatRequest): List<CatBreedView>

    suspend fun loadWallCatFromDatabase(): List<CatBreedView>
}