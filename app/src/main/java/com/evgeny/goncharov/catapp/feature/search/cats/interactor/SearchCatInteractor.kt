package com.evgeny.goncharov.catapp.feature.search.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents

interface SearchCatInteractor {

    fun clickNavigationBack()

    suspend fun setInputTextSearchView(text: String)

    fun getUiEventsLiveData(): LiveData<SearchCatEvents>

    fun getLiveDataCatsCathed(): LiveData<List<CatCatched>>

    fun chooseCat(id: String)
}