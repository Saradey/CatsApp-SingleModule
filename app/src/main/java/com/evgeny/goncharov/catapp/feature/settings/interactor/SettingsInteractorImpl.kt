package com.evgeny.goncharov.catapp.feature.settings.interactor

import androidx.appcompat.app.AppCompatDelegate
import com.evgeny.goncharov.catapp.common.Language
import com.evgeny.goncharov.catapp.common.navigation.MainRouter
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.gateway.SettingsGateway
import com.evgeny.goncharov.catapp.feature.settings.ui.DialogChooseLanguageApp.Companion.INDEX_CHOOSE_EN
import com.evgeny.goncharov.catapp.feature.settings.ui.DialogChooseLanguageApp.Companion.INDEX_CHOOSE_RU
import javax.inject.Inject

class SettingsInteractorImpl @Inject constructor(
    private val gateway: SettingsGateway,
    private val mainRouter: MainRouter
) : SettingsInteractor {

    companion object {
        const val INDEX_LIGHT_DIALOG = 0
        const val INDEX_NIGHT_DIALOG = 1

        const val INDEX_RUSSIAN_DIALOG = 0
        const val INDEX_ENGLISH_DIALOG = 1
    }

    private var themeValue = gateway.getThemeModeAppNow().themeValue
    private var chooseLanguageIndex = initIndexLanguage()


    private fun initIndexLanguage(): Int {
        return when (gateway.getAppLanguage()) {
            Language.RU -> INDEX_RUSSIAN_DIALOG
            Language.EN -> INDEX_ENGLISH_DIALOG
        }
    }


    override fun clickBack() {
        mainRouter.onBackPressed()
    }


    override fun getThemeNow(): ThemeModel {
        return gateway.getThemeModeAppNow()
    }


    override fun onLight() {
        themeValue = AppCompatDelegate.MODE_NIGHT_NO
        gateway.saveChooseTheme(themeValue)
    }


    override fun onNight() {
        themeValue = AppCompatDelegate.MODE_NIGHT_YES
        gateway.saveChooseTheme(themeValue)
    }


    override fun getThemeValue(): Int = when (themeValue) {
        AppCompatDelegate.MODE_NIGHT_YES -> INDEX_NIGHT_DIALOG
        else -> INDEX_LIGHT_DIALOG
    }


    override fun getAppLanguage(): Language = gateway.getAppLanguage()


    override fun getTheme(): Int = themeValue


    override fun getSelectLanguage(): Int = gateway.getSelectLanguage()


    override fun chooseLanguage(itemIndex: Int) {
        chooseLanguageIndex = itemIndex
        when (itemIndex) {
            INDEX_CHOOSE_RU -> gateway.chooseLanguage(Language.RU)
            INDEX_CHOOSE_EN -> gateway.chooseLanguage(Language.EN)
        }
    }


    override fun getChooseLanguageIndex(): Int {
        return chooseLanguageIndex
    }

}