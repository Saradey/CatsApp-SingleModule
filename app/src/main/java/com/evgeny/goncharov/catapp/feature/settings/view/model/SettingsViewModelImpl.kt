package com.evgeny.goncharov.catapp.feature.settings.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.feature.settings.interactor.ISettingsInteractor
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.ui.SettingsFragment
import javax.inject.Inject

class SettingsViewModelImpl : ViewModel(), ISettingsViewModel {

    @Inject
    lateinit var interactor: ISettingsInteractor

    private val themeLiveDataModel: MutableLiveData<ThemeModel> = MutableLiveData()


    override fun initInjection() {
        SettingsFragment.component.inject(this)
    }


    override fun getThemeLiveData(): LiveData<ThemeModel> {
        return themeLiveDataModel
    }


    override fun clickBack() {
        interactor.clickBack()
    }
}