package com.evgeny.goncharov.catapp.feature.search.cats.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny.goncharov.catapp.feature.search.cats.di.SearchCatSubcomponent
import com.evgeny.goncharov.catapp.feature.search.cats.interactor.SearchCatInteractor
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchCatViewModelImpl : ViewModel(), SearchCatViewModel {

    @Inject
    lateinit var interactor: SearchCatInteractor

    private var job: Job? = null

    override fun initInject() {
        SearchCatSubcomponent.component?.inject(this)
    }

    override fun clickNavigationBack() {
        interactor.clickNavigationBack()
    }

    override fun setInputTextSearchView(text: String) {
        job?.cancel()
        job = viewModelScope.launch {
            interactor.setInputTextSearchView(text)
        }
    }

    override fun getUiEventsLiveData(): LiveData<SearchCatEvents> {
        return interactor.getUiEventsLiveData()
    }

    override fun getLiveDataCatsCathed(): LiveData<List<CatCatched>> {
        return interactor.getLiveDataCatsCathed()
    }

    override fun chooseCat(id: String) {
        interactor.chooseCat(id)
    }
}