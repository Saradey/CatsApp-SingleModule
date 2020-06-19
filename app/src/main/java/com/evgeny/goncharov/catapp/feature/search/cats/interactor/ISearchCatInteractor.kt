package com.evgeny.goncharov.catapp.feature.search.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatchedValueObject
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents

interface ISearchCatInteractor {

    fun clickNavigationBack()

    suspend fun setInputTextSearchView(text: String)

    fun getUiEventsLiveData(): LiveData<SearchCatEvents>

    fun getLiveDataCatsCathed(): LiveData<List<CatCatchedValueObject>>

    fun chooseCat(id: String)

}