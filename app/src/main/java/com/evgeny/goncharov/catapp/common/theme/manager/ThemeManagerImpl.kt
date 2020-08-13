package com.evgeny.goncharov.catapp.common.theme.manager

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.evgeny.goncharov.catapp.di.qualified.AppContext
import javax.inject.Inject

class ThemeManagerImpl @Inject constructor(
    @AppContext private val context: Context
) : ThemeManager {

    init {
        AppCompatDelegate.setDefaultNightMode(getThemeModeAppNow())
    }

    companion object {
        const val SHARED_THEME_PREFERENCES_NAME = "SHARED_THEME_PREFERENCES_NAME"
        const val MODE_NIGHT_NAME = "MODE_NIGHT_NAME"
    }

    override fun getThemeModeAppNow(): Int {
        val shared =
            context.getSharedPreferences(SHARED_THEME_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return shared.getInt(MODE_NIGHT_NAME, AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun setThemeMode(modeNight: Int) {
        val shared =
            context.getSharedPreferences(SHARED_THEME_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val edit = shared.edit()
        edit.putInt(MODE_NIGHT_NAME, modeNight)
        edit.apply()
        AppCompatDelegate.setDefaultNightMode(modeNight)
    }
}