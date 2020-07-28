package com.evgeny.goncharov.catapp.feature.settings.interactor

import androidx.appcompat.app.AppCompatDelegate
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.gateway.ISettingsGateway
import javax.inject.Inject

class SettingsInteractorImpl @Inject constructor(
    private val repository: ISettingsGateway,
    private val mainRouter: IMainRouter
) : ISettingsInteractor {

    companion object {
        const val INDEX_LIGHT_DIALOG = 0
        const val INDEX_NIGHT_DIALOG = 1
    }

    private var themeValue = repository.getThemeModeAppNow().themeValue


    override fun clickBack() {
        mainRouter.onBackPressed()
    }


    override fun getThemeNow(): ThemeModel {
        return repository.getThemeModeAppNow()
    }


    override fun onLight() {
        themeValue = AppCompatDelegate.MODE_NIGHT_NO
        repository.saveChooseTheme(themeValue)
    }


    override fun onNight() {
        themeValue = AppCompatDelegate.MODE_NIGHT_YES
        repository.saveChooseTheme(themeValue)
    }


    override fun getThemeValue(): Int = when (themeValue) {
        AppCompatDelegate.MODE_NIGHT_YES -> INDEX_NIGHT_DIALOG
        else -> INDEX_LIGHT_DIALOG
    }

}