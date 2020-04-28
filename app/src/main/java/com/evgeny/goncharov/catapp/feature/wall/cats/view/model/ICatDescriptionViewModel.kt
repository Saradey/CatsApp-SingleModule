package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel

interface ICatDescriptionViewModel {

    fun setCatId(catId: String)

    fun loadChooseCat()

    fun getCatDescriptionLiveData(): LiveData<CatDescriptionModel>

    fun initInjection()

    fun getLiveDataUiEvents(): LiveData<BaseEventsUi>

}