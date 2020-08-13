package com.evgeny.goncharov.catapp.feature.settings.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.common.SingleLiveEvent
import com.evgeny.goncharov.catapp.feature.settings.di.SettingsSubcomponent
import com.evgeny.goncharov.catapp.feature.settings.events.SettingUiEvents
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractor
import com.evgeny.goncharov.catapp.feature.settings.interactor.SettingsInteractorImpl
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import javax.inject.Inject

class SettingsViewModelImpl : ViewModel(), SettingsViewModel {

    @Inject
    lateinit var interactor: SettingsInteractor

    private val themeLiveDataModel = SingleLiveEvent<ThemeModel>()
    private val languageLiveData = SingleLiveEvent<Language>()
    private val uiLiveDataEvent = SingleLiveEvent<SettingUiEvents>()

    override fun initInjection() {
        SettingsSubcomponent.component?.inject(this)
    }

    override fun getThemeLiveData(): LiveData<ThemeModel> = themeLiveDataModel

    override fun clickBack() {
        interactor.clickBack()
    }

    override fun initThemeToView() {
        val theme = interactor.getThemeNow()
        themeLiveDataModel.value = theme
    }

    override fun getThemeValue(): Int = interactor.getThemeValue()

    override fun setChooseThemeIndex(item: Int) {
        when (item) {
            SettingsInteractorImpl.INDEX_NIGHT_DIALOG -> interactor.onNight()
            else -> interactor.onLight()
        }
    }

    override fun getLanguageLiveData(): LiveData<Language> {
        return languageLiveData
    }

    override fun initLanguageToView() {
        val lan = interactor.getAppLanguage()
        languageLiveData.value = lan
    }

    override fun getUiEvents(): LiveData<SettingUiEvents> = uiLiveDataEvent

    override fun getThemeNow(): Int = interactor.getTheme()

    override fun getSelectLanguage(): Int = interactor.getSelectLanguage()

    override fun chooseLanguage(itemIndex: Int) {
        if (interactor.getChooseLanguageIndex() != itemIndex) {
            interactor.chooseLanguage(itemIndex)
            uiLiveDataEvent.value = SettingUiEvents.ChooseLanguageApp
        }
    }
}