package com.evgeny.goncharov.catapp.feature.settings.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.interactor.ISettingsInteractor
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractorImpl
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import javax.inject.Inject

class SettingsViewModelImpl : ViewModel(), ISettingsViewModel {

    @Inject
    lateinit var interactor: ISettingsInteractor

    private val themeLiveDataModel: MutableLiveData<ThemeModel> = MutableLiveData()


    override fun initInjection() {
        SettingsSubcomponent.component?.inject(this)
    }


    override fun getThemeLiveData(): LiveData<ThemeModel> {
        return themeLiveDataModel
    }


    override fun clickBack() {
        interactor.clickBack()
    }


    override fun initThemeToView() {
        val theme = interactor.getThemeNow()
        themeLiveDataModel.postValue(theme)
    }


    override fun getThemeValue(): Int = interactor.getThemeValue()


    override fun setChooseThemeIndex(item: Int) {
        when (item) {
            SettingsInteractorImpl.INDEX_NIGHT_DIALOG -> interactor.onNight()
            else -> interactor.onLight()
        }
    }

}