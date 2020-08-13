package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.common.navigation.MainRouter
import com.evgeny.goncharov.catapp.feature.wall.cats.gateway.CatDescriptionGateway
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescription
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatDescriptionInteractorImpl @Inject constructor(
    private val repository: CatDescriptionGateway,
    private val router: MainRouter
) : CatDescriptionInteractor {

    private var catId = ""

    private var liveDataUiEvents = SingleLiveEvent<CatDescriptionEvents>()

    override fun setCatId(catId: String) {
        this.catId = catId
    }

    override suspend fun loadChooseCat(): CatDescription? =
        withContext(Dispatchers.Main) {
            var cat: CatDescription? = null
            liveDataUiEvents.value = CatDescriptionEvents.EventShowProgress
            cat = try {
                repository.loadChooseCatFromInternet(catId)
            } catch (exception: Exception) {
                exception.printStackTrace()
                loadChooseCatFromDatabase()
            }
            validateData(cat)
            cat
        }

    private suspend fun loadChooseCatFromDatabase(): CatDescription? {
        val model = repository.loadChooseCatFromDatabase(catId)
        return model ?: kotlin.run {
            repository.loadChooseCatFromDatabaseSpare(catId)
        }
    }

    private fun validateData(model: CatDescription?) {
        if (model == null) {
            liveDataUiEvents.value = CatDescriptionEvents.EventHideProgressAndShowSomethingWrong
        } else {
            liveDataUiEvents.value = CatDescriptionEvents.EventHideProgressAndShowContent
        }
    }

    override fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents> {
        return liveDataUiEvents
    }

    override fun clickBack() {
        router.onBackPressed()
    }
}