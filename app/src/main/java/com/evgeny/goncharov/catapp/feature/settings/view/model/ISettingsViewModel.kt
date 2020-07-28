package com.evgeny.goncharov.catapp.feature.settings.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.feature.settings.events.SettingUiEvents
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface ISettingsViewModel {

    fun initInjection()

    fun getThemeLiveData(): LiveData<ThemeModel>

    fun clickBack()

    fun initThemeToView()

    fun getThemeValue(): Int

    fun setChooseThemeIndex(item: Int)

    fun getLanguageLiveData(): LiveData<Language>

    fun initLanguageToView()

    fun getUiEvents(): LiveData<SettingUiEvents>

    fun getThemeNow(): Int

}