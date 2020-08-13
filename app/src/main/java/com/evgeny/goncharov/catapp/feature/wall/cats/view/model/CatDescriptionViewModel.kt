package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescription
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents

interface CatDescriptionViewModel {

    fun setCatId(catId: String)

    fun loadChooseCat()

    fun getCatDescriptionLiveData(): LiveData<CatDescription>

    fun initInjection()

    fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents>

    fun clickBack()
}