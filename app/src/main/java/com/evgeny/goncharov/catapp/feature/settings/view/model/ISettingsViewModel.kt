package com.evgeny.goncharov.catapp.feature.settings.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeDTO

interface ISettingsViewModel {

    fun initInjection()

    fun getThemeLiveData(): LiveData<ThemeDTO>

    fun clickBack()

    fun initThemeToView()

    fun onLight()

    fun onNight()

    fun clickButtonDone()

}