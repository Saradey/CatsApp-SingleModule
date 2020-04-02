package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.IWallCatInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.WallCatsFragment
import javax.inject.Inject

class WallCatsViewModelImpl : ViewModel(), IWallCatsViewModel {

    @Inject
    lateinit var interactor: IWallCatInteractor


    init {
        WallCatsFragment.component.inject(this)
    }

    
}