package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel

interface ICatDescriptionInteractor {

    fun setCatId(catId: String)

    suspend fun loadChooseCat(): CatDescriptionModel?

    fun getLiveDataUiEvents(): LiveData<BaseEventsUi>

    fun clickBack()

}