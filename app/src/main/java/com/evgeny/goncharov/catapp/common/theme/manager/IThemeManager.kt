package com.evgeny.goncharov.catapp.common.theme.manager

interface IThemeManager {

    fun getThemeModeAppNow(): Int

    fun setThemeMode(modeNight: Int)

}