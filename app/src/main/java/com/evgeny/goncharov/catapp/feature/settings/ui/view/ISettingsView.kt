package com.evgeny.goncharov.catapp.feature.settings.ui.view

import com.evgeny.goncharov.catapp.base.IBaseView
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface ISettingsView : IBaseView {

    fun setThemeModel(it: ThemeModel)

}