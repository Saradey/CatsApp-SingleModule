package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.consts.LIMIT_PAGE_SIZE_CAT_WALL
import com.evgeny.goncharov.catapp.feature.wall.cats.model.request.WallCatRequest
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatBreedView
import com.evgeny.goncharov.catapp.feature.wall.cats.gateway.IWallCatGateway
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.WallCatsEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WallCatInteractorImpl @Inject constructor(
    private val repository: IWallCatGateway,
    private val mainRouter: IMainRouter
) : IWallCatInteractor {


    private val liveDataUiEvents = SingleLiveEvent<WallCatsEvents>()


    override suspend fun loadWallCat(): List<CatBreedView> {
        showProgress()
        var listModels: List<CatBreedView> = emptyList()
        listModels = try {
            loadFromInternet()
        } catch (exp: Exception) {
            loadFromDatabase(exp)
        } finally {
            hideProgressAndInitRefreshLayout()
        }
        changeStateView(listModels)
        return suspendCoroutine { continuation ->
            continuation.resume(listModels)
        }
    }


    private fun changeStateView(listModels: List<CatBreedView>) {
        if (listModels.isEmpty()) {
            liveDataUiEvents.value = WallCatsEvents.EventSomethingWrong
        }
    }


    override suspend fun loadNexPage(key: Int): List<CatBreedView> {
        val result = repository.loadWallCatFromInternet(
            WallCatRequest(
                limit = LIMIT_PAGE_SIZE_CAT_WALL,
                page = key
            )
        )
        return suspendCoroutine { continuation ->
            continuation.resume(
                result
            )
        }
    }


    private suspend fun loadFromDatabase(exp: Exception): List<CatBreedView> {
        exp.printStackTrace()
        return repository.loadWallCatFromDatabase()
    }


    private suspend fun loadFromInternet(): List<CatBreedView> {
        return repository.loadWallCatFromInternet(
            WallCatRequest(
                limit = LIMIT_PAGE_SIZE_CAT_WALL,
                page = 0
            )
        )
    }


    private suspend fun showProgress() = withContext(Dispatchers.Main) {
        liveDataUiEvents.value = WallCatsEvents.EventShowProgressAndHideStub
    }


    private suspend fun hideProgressAndInitRefreshLayout() = withContext(Dispatchers.Main) {
        liveDataUiEvents.value = WallCatsEvents.EventHideProgressAndInitRefreshLayout
    }


    override fun clickCatBreed(id: String) {
        mainRouter.showCatDescription(id)
    }


    override fun getUiEventsLiveData(): LiveData<WallCatsEvents> {
        return liveDataUiEvents
    }


    override fun clickMenuSearchCat() {
        mainRouter.goToTheSearchCatFragment()
    }


    override fun clickMenuSettings() {
        mainRouter.goToTheSettingFragment()
    }
}

