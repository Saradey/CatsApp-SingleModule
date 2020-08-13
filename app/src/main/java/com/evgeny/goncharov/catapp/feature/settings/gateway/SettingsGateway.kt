package com.evgeny.goncharov.catapp.feature.settings.gateway

import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface SettingsGateway {

    fun getThemeModeAppNow(): ThemeModel

    fun saveChooseTheme(themeValue: Int)

    fun getAppLanguage(): Language

    fun getSelectLanguage(): Int

    fun chooseLanguage(lang: Language)
}