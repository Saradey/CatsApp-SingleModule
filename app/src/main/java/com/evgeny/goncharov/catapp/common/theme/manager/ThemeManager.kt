package com.evgeny.goncharov.catapp.common.theme.manager

interface ThemeManager {

    fun getThemeModeAppNow(): Int

    fun setThemeMode(modeNight: Int)

}