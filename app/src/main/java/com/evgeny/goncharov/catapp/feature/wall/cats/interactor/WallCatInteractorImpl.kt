package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.MutableLiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.consts.LIMIT_PAGE_SIZE_CAT_WALL
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.IWallCatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WallCatInteractorImpl @Inject constructor(
    private val repository: IWallCatRepository,
    private val liveDataUiEvents: MutableLiveData<BaseEventsUi>
) : IWallCatInteractor {

    private var listModels: List<CatBreedModel> = listOf()


    override suspend fun initWallCat(): List<CatBreedModel> {
        repository.getLiveDataCatBreedModel().observeForever {
            listModels = it
        }
        try {
            showProgress()
            repository.loadWallCat(WallCatRequest(limit = LIMIT_PAGE_SIZE_CAT_WALL, page = 0))
        } catch (exp: Exception) {
            exp.printStackTrace()
        } finally {
            hideProgress()
        }
        return suspendCoroutine { continuation ->
            continuation.resume(listModels)
        }
    }


    private suspend fun showProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.postValue(BaseEventsUi.EventsShowProgress)
    }


    private suspend fun hideProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.postValue(BaseEventsUi.EventsHideProgress)
    }


}

