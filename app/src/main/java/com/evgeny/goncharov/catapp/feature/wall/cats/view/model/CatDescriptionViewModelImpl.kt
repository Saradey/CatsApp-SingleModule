package com.evgeny.goncharov.catapp.feature.wall.cats.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny.goncharov.catapp.feature.wall.cats.di.components.CatDescriptionSubcomponent
import com.evgeny.goncharov.catapp.feature.wall.cats.interactor.ICatDescriptionInteractor
import com.evgeny.goncharov.catapp.feature.wall.cats.model.to.view.CatDescriptionModel
import com.evgeny.goncharov.catapp.feature.wall.cats.ui.events.CatDescriptionEvents
import kotlinx.coroutines.launch
import javax.inject.Inject


class CatDescriptionViewModelImpl : ViewModel(), ICatDescriptionViewModel {

    private val catDescriptionLiveData = MutableLiveData<CatDescriptionModel>()


    @Inject
    lateinit var interactor: ICatDescriptionInteractor


    override fun setCatId(catId: String) {
        interactor.setCatId(catId)
    }


    override fun loadChooseCat() {
        viewModelScope.launch {
            val cat = interactor.loadChooseCat()
            cat?.let {
                catDescriptionLiveData.postValue(cat)
            }
        }
    }


    override fun getCatDescriptionLiveData(): LiveData<CatDescriptionModel> {
        return catDescriptionLiveData
    }


    override fun initInjection() {
        CatDescriptionSubcomponent.component?.inject(this)
    }


    override fun getLiveDataUiEvents(): LiveData<CatDescriptionEvents> {
        return interactor.getLiveDataUiEvents()
    }


    override fun clickBack() {
        interactor.clickBack()
    }
}