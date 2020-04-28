package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel

interface IWallCatInteractor {

    suspend fun loadWallCat(): List<CatBreedModel>

    suspend fun loadNexPage(key: Int): List<CatBreedModel>

    fun clickCatBreed(id: String)

    fun getUiEventsLiveData(): LiveData<BaseEventsUi>

}