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


    override suspend fun loadWallCat(): List<CatBreedModel> {
        var listModels: List<CatBreedModel> = listOf()
        listModels = try {
            loadFromInternet()
        } catch (exp: Exception) {
            loadFromDatabase(exp)
        } finally {
            hideProgress()
        }
        return suspendCoroutine { continuation ->
            continuation.resume(listModels)
        }
    }


    private suspend fun loadFromDatabase(exp: Exception): List<CatBreedModel> {
        exp.printStackTrace()
        val result = repository.loadWallCatFromDatabase()
        return result
    }


    private suspend fun loadFromInternet(): List<CatBreedModel> {
        showProgress()
        return repository.loadWallCatFromInternet(
            WallCatRequest(
                limit = LIMIT_PAGE_SIZE_CAT_WALL,
                page = 0
            )
        )
    }


    private suspend fun showProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.postValue(BaseEventsUi.EventsShowProgress)
    }


    private suspend fun hideProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.postValue(BaseEventsUi.EventsHideProgress)
    }


}

