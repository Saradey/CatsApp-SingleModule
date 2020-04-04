package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.consts.LIMIT_PAGE_SIZE_CAT_WALL
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedModel
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.IWallCatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class WallCatInteractorImpl @Inject constructor(
    private val repository: IWallCatRepository,
    private val liveDataUiEvents: MutableLiveData<BaseEventsUi>
) : IWallCatInteractor {

    override suspend fun initWallCat() {
        try {
            showProgress()
            repository.loadWallCat(WallCatRequest(limit = LIMIT_PAGE_SIZE_CAT_WALL, page = 0))
        } catch (exp: Exception) {
            exp.printStackTrace()
        } finally {
            hideProgress()
        }
    }


    private suspend fun showProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.postValue(BaseEventsUi.EventsShowProgress)
    }


    private suspend fun hideProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.postValue(BaseEventsUi.EventsHideProgress)
    }


    override fun getLiveDataCatBreedModel(): LiveData<List<CatBreedModel>> {
        return repository.getLiveDataCatBreedModel()
    }

}

