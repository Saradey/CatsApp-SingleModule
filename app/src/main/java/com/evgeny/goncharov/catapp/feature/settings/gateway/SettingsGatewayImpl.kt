package com.evgeny.goncharov.catapp.feature.settings.gateway

import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.common.language.manager.ILanguageManager
import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.consts.RU_CODE
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import javax.inject.Inject

class SettingsGatewayImpl @Inject constructor(
    private val themeManager: IThemeManager,
    private val languageManager: ILanguageManager
) : ISettingsGateway {


    override fun getThemeModeAppNow(): ThemeModel {
        return ThemeModel(
            themeManager.getThemeModeAppNow()
        )
    }


    override fun saveChooseTheme(themeValue: Int) {
        themeManager.setThemeMode(themeValue)
    }


    override fun getAppLanguage(): Language {
        return when (languageManager.getAppLanguage()) {
            RU_CODE -> Language.RU
            else -> Language.EN
        }
    }

}