package com.evgeny.goncharov.catapp.feature.settings.gateway

import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.common.language.manager.LanguageManager
import com.evgeny.goncharov.catapp.common.theme.manager.ThemeManager
import com.evgeny.goncharov.catapp.consts.RU_CODE
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.ui.DialogChooseLanguageApp.Companion.INDEX_CHOOSE_EN
import com.evgeny.goncharov.catapp.feature.settings.ui.DialogChooseLanguageApp.Companion.INDEX_CHOOSE_RU
import javax.inject.Inject

class SettingsGatewayImpl @Inject constructor(
    private val themeManager: ThemeManager,
    private val languageManager: LanguageManager
) : SettingsGateway {


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


    override fun getSelectLanguage(): Int {
        return when (languageManager.getAppLanguageEnum()) {
            Language.RU -> INDEX_CHOOSE_RU
            Language.EN -> INDEX_CHOOSE_EN
        }
    }


    override fun chooseLanguage(lang: Language) {
        languageManager.chooseLanguage(lang.code)
    }


}