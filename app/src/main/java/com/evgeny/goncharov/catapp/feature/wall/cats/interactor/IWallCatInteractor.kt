package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

interface IWallCatInteractor {

    suspend fun initWallCat(): List<CatBreedModel>

}