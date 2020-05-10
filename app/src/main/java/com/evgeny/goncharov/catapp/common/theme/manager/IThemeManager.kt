package com.evgeny.goncharov.catapp.common.theme.manager

import com.evgeny.goncharov.catapp.MainActivity

interface IThemeManager {

    fun getThemeModeAppNow(): Int

    fun setThemeMode(modeNight: Int)

}