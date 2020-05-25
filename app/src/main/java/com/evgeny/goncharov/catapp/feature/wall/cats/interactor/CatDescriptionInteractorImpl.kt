package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.ICatDescriptionRepository
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatDescriptionInteractorImpl @Inject constructor(
    private val repository: ICatDescriptionRepository,
    private val router: IMainRouter
) : ICatDescriptionInteractor {

    private var catId = ""

    private var liveDataUiEvents = SingleLiveEvent<CatDescriptionEvents>()


    override fun setCatId(catId: String) {
        this.catId = catId
    }


    override suspend fun loadChooseCat(): CatDescriptionModel? = withContext(Dispatchers.Main) {
        var cat: CatDescriptionModel? = null
        liveDataUiEvents.postValue(CatDescriptionEvents.EventShowProgress)
        cat = try {
            repository.loadChooseCatFromInternet(catId)
        } catch (exception: Exception) {
            exception.printStackTrace()
            loadChooseCatFromDatabase()
        }
        validateData(cat)
        cat
    }


    private suspend fun loadChooseCatFromDatabase(): CatDescriptionModel? {
        val model = repository.loadChooseCatFromDatabase(catId)
        return model ?: kotlin.run {
            repository.loadChooseCatFromDatabaseSpare(catId)
        }
    }


    private fun validateData(model: CatDescriptionModel?) {
        if (model == null) {
            liveDataUiEvents.postValue(CatDescriptionEvents.EventHideProgressAndShowSomethingWrong)
        } else {
            liveDataUiEvents.postValue(CatDescriptionEvents.EventHideProgressAndShowContent)
        }
    }


    override fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents> {
        return liveDataUiEvents
    }


    override fun clickBack() {
        router.onBackPressed()
    }
}