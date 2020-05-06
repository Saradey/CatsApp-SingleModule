package com.evgeny.goncharov.catapp.feature.settings.interactor

import androidx.appcompat.app.AppCompatDelegate
import com.evgeny.goncharov.catapp.common.navigation.IMainRouter
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import com.evgeny.goncharov.catapp.feature.settings.repository.ISettingsRepository
import javax.inject.Inject

class SettingsInteractorImpl @Inject constructor(
    private val repository: ISettingsRepository,
    private val mainRouter: IMainRouter
) : ISettingsInteractor {

    private var themeValue = repository.getThemeModeAppNow().themeValue


    override fun clickBack() {
        mainRouter.onBackPressed()
    }


    override fun getThemeNow(): ThemeModel {
        return repository.getThemeModeAppNow()
    }


    override fun onLight() {
        themeValue = AppCompatDelegate.MODE_NIGHT_NO
    }


    override fun onNight() {
        themeValue = AppCompatDelegate.MODE_NIGHT_YES
    }


    override fun clickButtonDone() {
        repository.saveChooseTheme(themeValue)
    }
}