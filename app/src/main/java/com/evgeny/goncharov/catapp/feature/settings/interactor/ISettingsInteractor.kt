package com.evgeny.goncharov.catapp.feature.settings.interactor

import com.evgeny.goncharov.catapp.feature.settings.models.ThemeDTO

interface ISettingsInteractor {

    fun clickBack()

    fun getThemeNow(): ThemeDTO

    fun onLight()

    fun onNight()

    fun clickButtonDone()

}