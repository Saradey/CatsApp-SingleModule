package com.evgeny.goncharov.catapp.feature.settings.repository

import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val themeManager: IThemeManager
) : ISettingsRepository {


    override fun getThemeModeAppNow(): ThemeModel {
        return ThemeModel(
            themeManager.getThemeModeAppNow()
        )
    }


    override fun saveChooseTheme(themeValue: Int) {
        themeManager.setThemeMode(themeValue)
    }

}