package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.WallCatsSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WallCatsViewModelImpl : ViewModel(), IWallCatsViewModel {

    @Inject
    lateinit var interactor: IWallCatInteractor


    override fun initInject() {
        WallCatsSubcomponent.component?.inject(this)
    }


    override suspend fun initWallCat(): List<CatBreedModel> {
        val result = interactor.loadWallCat()
        return suspendCoroutine { continuation ->
            continuation.resume(result)
        }
    }


    override suspend fun loadNextCats(key: Int): List<CatBreedModel> {
        val result = interactor.loadNexPage(key)
        return suspendCoroutine { continuation ->
            continuation.resume(result)
        }
    }


    override fun clickCatBreed(id: String) {
        interactor.clickCatBreed(id)
    }


    override fun getUiEventsLiveData(): LiveData<WallCatsEvents> {
        return interactor.getUiEventsLiveData()
    }


    override fun clickMenuSearchCat() {
        interactor.clickMenuSearchCat()
    }


    override fun clickMenuSettings() {
        interactor.clickMenuSettings()
    }
}