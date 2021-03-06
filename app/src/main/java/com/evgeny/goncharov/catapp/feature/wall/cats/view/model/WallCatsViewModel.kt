package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.WallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WallCatsViewModel @Inject constructor(
    private var interactor: WallCatInteractor
) : ViewModel() {


    suspend fun initWallCat(): List<CatBreedView> {
        val result = interactor.loadWallCat()
        return suspendCoroutine { continuation ->
            continuation.resume(result)
        }
    }

    suspend fun loadNextCats(key: Int): List<CatBreedView> {
        val result = interactor.loadNexPage(key)
        return suspendCoroutine { continuation ->
            continuation.resume(result)
        }
    }

    fun clickCatBreed(id: String) {
        interactor.clickCatBreed(id)
    }

    fun getUiEventsLiveData(): LiveData<WallCatsEvents> {
        return interactor.getUiEventsLiveData()
    }

    fun clickMenuSearchCat() {
        interactor.clickMenuSearchCat()
    }

    fun clickMenuSettings() {
        interactor.clickMenuSettings()
    }
}