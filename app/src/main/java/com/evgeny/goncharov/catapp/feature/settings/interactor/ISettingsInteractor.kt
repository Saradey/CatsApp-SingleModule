package com.evgeny.goncharov.catapp.feature.settings.interactor

import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface ISettingsInteractor {

    fun clickBack()

    fun getThemeNow(): ThemeModel

    fun onLight()

    fun onNight()

    fun getThemeValue(): Int

}