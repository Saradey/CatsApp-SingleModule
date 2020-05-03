package com.evgeny.goncharov.catapp.feature.search.cats.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.feature.search.cats.model.CatCatched
import com.evgeny.goncharov.catapp.feature.search.cats.repository.ISearchCatRepository
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.GetChooseCatRequest
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class SearchCatInteractorImpl @Inject constructor(
    private val repository: ISearchCatRepository,
    private val router: IMainRouter
) : ISearchCatInteractor {

    private val liveDataUiEvents = SingleLiveEvent<BaseEventsUi>()
    private val liveDataCatsCathed = MutableLiveData<List<CatCatched>>()


    override fun clickNavigationBack() {
        router.onBackPressed()
    }


    override suspend fun setInputTextSearchView(text: String) {
        liveDataUiEvents.postValue(BaseEventsUi.EventShowProgressAndHideStubAndHideModels)
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
            liveDataUiEvents.postValue(BaseEventsUi.EventHideProgressAndShowStub)
        } else {
            liveDataUiEvents.postValue(BaseEventsUi.EventHideProgressAndShowRecycleView)
            liveDataCatsCathed.postValue(models)
        }
    }


    override fun getUiEventsLiveData(): LiveData<BaseEventsUi> {
        return liveDataUiEvents
    }


    override fun getLiveDataCatsCathed(): LiveData<List<CatCatched>> {
        return liveDataCatsCathed
    }
}