package com.evgeny.goncharov.catapp.feature.settings.gateway

import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface ISettingsGateway {

    fun getThemeModeAppNow(): ThemeModel

    fun saveChooseTheme(themeValue: Int)

}