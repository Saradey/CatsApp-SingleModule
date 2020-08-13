package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescription
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents

interface CatDescriptionInteractor {

    fun setCatId(catId: String)

    suspend fun loadChooseCat(): CatDescription?

    fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents>

    fun clickBack()
}