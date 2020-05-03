package com.evgeny.goncharov.catapp.feature.search.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched

interface ISearchCatInteractor {

    fun clickNavigationBack()

    suspend fun setInputTextSearchView(text: String)

    fun getUiEventsLiveData(): LiveData<BaseEventsUi>

    fun getLiveDataCatsCathed(): LiveData<List<CatCatched>>

    fun chooseCat(id: String)

}