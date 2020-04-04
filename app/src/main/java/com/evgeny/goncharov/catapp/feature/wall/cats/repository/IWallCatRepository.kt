package com.evgeny.goncharov.catapp.feature.wall.cats.repository

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

interface IWallCatRepository {

    suspend fun loadWallCat(request: WallCatRequest)

    fun getLiveDataCatBreedModel(): LiveData<List<CatBreedModel>>

}