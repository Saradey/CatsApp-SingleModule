package com.evgeny.goncharov.catapp.feature.search.cats.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents


interface ISearchCatViewModel {

    fun initInject()

    fun clickNavigationBack()

    fun setInputTextSearchView(text: String)

    fun getUiEventsLiveData(): LiveData<SearchCatEvents>

    fun getLiveDataCatsCathed(): LiveData<List<CatCatched>>

    fun chooseCat(id: String)

}