package com.evgeny.goncharov.catapp.feature.settings.gateway

import com.evgeny.goncharov.catapp.feature.settings.models.ThemeDTO

interface ISettingsGateway {

    fun getThemeModeAppNow(): ThemeDTO

    fun saveChooseTheme(themeValue: Int)

}