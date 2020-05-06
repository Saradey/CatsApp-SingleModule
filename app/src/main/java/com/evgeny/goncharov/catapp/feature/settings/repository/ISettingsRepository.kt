package com.evgeny.goncharov.catapp.feature.settings.repository

import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface ISettingsRepository {

    fun getThemeModeAppNow(): ThemeModel

    fun saveChooseTheme(themeValue: Int)

}