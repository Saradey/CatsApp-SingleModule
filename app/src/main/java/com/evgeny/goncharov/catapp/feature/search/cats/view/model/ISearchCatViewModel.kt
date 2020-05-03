package com.evgeny.goncharov.catapp.feature.search.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched

interface ISearchCatViewModel {

    fun initInject()

    fun clickNavigationBack()

    fun setInputTextSearchView(text: String)

    fun getUiEventsLiveData(): LiveData<BaseEventsUi>

    fun getLiveDataCatsCathed(): LiveData<List<CatCatched>>

    fun chooseCat(id: String)

}