package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

    interface IWallCatsViewModel {

    suspend fun initWallCat() : List<CatBreedModel>

}