package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents

interface WallCatInteractor {

    suspend fun loadWallCat(): List<CatBreedView>

    suspend fun loadNexPage(key: Int): List<CatBreedView>

    fun clickCatBreed(id: String)

    fun getUiEventsLiveData(): LiveData<WallCatsEvents>

    fun clickMenuSearchCat()

    fun clickMenuSettings()
}