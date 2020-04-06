package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WallCatsViewModelImpl : ViewModel(), IWallCatsViewModel {

    @Inject
    lateinit var interactor: IWallCatInteractor

    init {
        WallCatsFragment.component.inject(this)
    }


    override suspend fun initWallCat() = withContext<List<CatBreedModel>>(Dispatchers.Main) {
        val result = interactor.initWallCat()
        result
    }


}