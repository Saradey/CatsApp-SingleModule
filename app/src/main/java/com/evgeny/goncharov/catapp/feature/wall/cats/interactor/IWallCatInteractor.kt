package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedValueObject
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents

interface IWallCatInteractor {

    suspend fun loadWallCat(): List<CatBreedValueObject>

    suspend fun loadNexPage(key: Int): List<CatBreedValueObject>

    fun clickCatBreed(id: String)

    fun getUiEventsLiveData(): LiveData<WallCatsEvents>

    fun clickMenuSearchCat()

    fun clickMenuSettings()

}