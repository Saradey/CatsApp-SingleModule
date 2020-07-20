package com.evgeny.goncharov.catapp.feature.search.cats.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.gateway.ISearchCatGateway
import com.evgeny.goncharov.catapp.feature.search.cats.ui.events.SearchCatEvents
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.GetChooseCatRequest
import javax.inject.Inject

class SearchCatInteractorImpl @Inject constructor(
    private val repository: ISearchCatGateway,
    private val router: IMainRouter
) : ISearchCatInteractor {

    private val liveDataUiEvents = SingleLiveEvent<SearchCatEvents>()
    private val liveDataCatsCathed = MutableLiveData<List<CatCatched>>()


    override fun clickNavigationBack() {
        router.onBackPressed()
    }


    override suspend fun setInputTextSearchView(text: String) {
        liveDataUiEvents.value = SearchCatEvents.EventShowProgressAndHideStubAndHideModels
        val models = try {
            repository.loadFromInternet(GetChooseCatRequest(text).createRequest())
        } catch (exp: Exception) {
            exp.printStackTrace()
            repository.loadFromDatabase(text)
        }
        validateData(models)
    }


    private fun validateData(models: List<CatCatched>) {
        if (models.isEmpty()) {
            liveDataUiEvents.value = SearchCatEvents.EventHideProgressAndShowStub
        } else {
            liveDataUiEvents.value = SearchCatEvents.EventHideProgressAndShowRecycleView
            liveDataCatsCathed.postValue(models)
        }
    }


    override fun getUiEventsLiveData(): LiveData<SearchCatEvents> {
        return liveDataUiEvents
    }


    override fun getLiveDataCatsCathed(): LiveData<List<CatCatched>> {
        return liveDataCatsCathed
    }


    override fun chooseCat(id: String) {
        router.showCatDescription(id)
    }
}