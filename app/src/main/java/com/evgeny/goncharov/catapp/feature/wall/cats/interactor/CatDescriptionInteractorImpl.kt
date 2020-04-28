package com.evgeny.goncharov.catapp.feature.wall.cats.interactor

import androidx.lifecycle.MutableLiveData
import com.evgeny.goncharov.catapp.base.BaseEventsUi
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel
import com.evgeny.goncharov.catapp.feature.wall.cats.repository.ICatDescriptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CatDescriptionInteractorImpl @Inject constructor(
    private val repository: ICatDescriptionRepository,
    private val liveDataUiEvents: MutableLiveData<BaseEventsUi>
) : ICatDescriptionInteractor {

    private var catId = ""


    override fun setCatId(catId: String) {
        this.catId = catId
    }


    override suspend fun loadChooseCat(): CatDescriptionModel? = withContext(Dispatchers.Main) {
        var cat: CatDescriptionModel? = null
        cat = try {
            liveDataUiEvents.postValue(BaseEventsUi.EventsShowProgress)
            repository.loadChooseCatFromInternet(catId)
        } catch (exception: Exception) {
            exception.printStackTrace()
            repository.loadChooseCatFromDatabase(catId)
        } finally {
            liveDataUiEvents.postValue(BaseEventsUi.EventsHideProgress)
        }
        validateData(cat)
        cat
    }


    private suspend fun validateData(model: CatDescriptionModel?) {
        if (model == null) {
            liveDataUiEvents.postValue(BaseEventsUi.SomethingWrong)
        }
    }

}