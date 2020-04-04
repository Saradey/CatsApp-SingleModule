package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

interface IWallCatsViewModel {

    fun initWallCat()

    fun getCatWallLiveData(): LiveData<List<CatBreedModel>>

}