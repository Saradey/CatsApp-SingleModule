package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import kotlinx.coroutines.*
import javax.inject.Inject

class WallCatsViewModelImpl : ViewModel(), IWallCatsViewModel {

    @Inject
    lateinit var interactor: IWallCatInteractor

    init {
        WallCatsFragment.component.inject(this)
    }


    override fun initWallCat() {
        viewModelScope.launch {
            loadWallCat()
        }
    }


    private suspend fun loadWallCat() = withContext(Dispatchers.IO) {
        interactor.initWallCat()
    }


    override fun getCatWallLiveData(): LiveData<List<CatBreedModel>> {
        return interactor.getLiveDataCatBreedModel()
    }
}