package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

interface IWallCatInteractor {

    suspend fun initWallCat()

    fun getLiveDataCatBreedModel(): LiveData<List<CatBreedModel>>

}