package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionDTO
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents

interface ICatDescriptionViewModel {

    fun setCatId(catId: String)

    fun loadChooseCat()

    fun getCatDescriptionLiveData(): LiveData<CatDescriptionDTO>

    fun initInjection()

    fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents>

    fun clickBack()

}