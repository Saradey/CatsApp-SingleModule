package com.evgeny.goncharov.catapp.feature.wall.cats.gateway

import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedValueObject

interface IWallCatGateway {

    suspend fun loadWallCatFromInternet(request: WallCatRequest): List<CatBreedValueObject>

    suspend fun loadWallCatFromDatabase(): List<CatBreedValueObject>

}