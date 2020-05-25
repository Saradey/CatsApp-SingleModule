package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents

interface IWallCatsViewModel {

    suspend fun initWallCat(): List<CatBreedModel>

    suspend fun loadNextCats(key: Int): List<CatBreedModel>

    fun clickCatBreed(id: String)

    fun initInject()

    fun getUiEventsLiveData(): LiveData<WallCatsEvents>

    fun clickMenuSearchCat()

    fun clickMenuSettings()

}