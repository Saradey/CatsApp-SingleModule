package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents

interface IWallCatInteractor {

    suspend fun loadWallCat(): List<CatBreedModel>

    suspend fun loadNexPage(key: Int): List<CatBreedModel>

    fun clickCatBreed(id: String)

    fun getUiEventsLiveData(): LiveData<WallCatsEvents>

    fun clickMenuSearchCat()

    fun clickMenuSettings()

}