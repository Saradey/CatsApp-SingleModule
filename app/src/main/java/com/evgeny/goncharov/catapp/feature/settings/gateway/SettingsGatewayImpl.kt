package com.evgeny.goncharov.catapp.feature.settings.gateway

import com.evgeny.goncharov.catapp.common.theme.manager.IThemeManager
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeDTO
import javax.inject.Inject

class SettingsGatewayImpl @Inject constructor(
    private val themeManager: IThemeManager
) : ISettingsGateway {


    override fun getThemeModeAppNow(): ThemeDTO {
        return ThemeDTO(
            themeManager.getThemeModeAppNow()
        )
    }


    override fun saveChooseTheme(themeValue: Int) {
        themeManager.setThemeMode(themeValue)
    }

}