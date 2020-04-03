package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WallCatsViewModelImpl : ViewModel(), IWallCatsViewModel {

    @Inject
    lateinit var interactor: IWallCatInteractor

    private val liveDateWallCat = MutableLiveData<List<CatBreedModel>>()

    init {
        WallCatsFragment.component.inject(this)
    }


    override fun initWallCat() {
        viewModelScope.launch {
            loadWallCat()
        }
    }


    private suspend fun loadWallCat() = withContext(Dispatchers.IO) {
        val result = interactor.initWallCat()
        liveDateWallCat.postValue(result)
    }


    override fun getCatWallLiveData(): MutableLiveData<List<CatBreedModel>> {
        return liveDateWallCat
    }
}