package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents

interface ICatDescriptionInteractor {

    fun setCatId(catId: String)

    suspend fun loadChooseCat(): CatDescriptionModel?

    fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents>

    fun clickBack()

}