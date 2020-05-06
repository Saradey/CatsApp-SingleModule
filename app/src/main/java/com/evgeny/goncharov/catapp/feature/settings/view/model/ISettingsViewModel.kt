package com.evgeny.goncharov.catapp.feature.settings.view.model

import androidx.lifecycle.LiveData
import com.evgeny.goncharov.catapp.feature.settings.models.ThemeModel

interface ISettingsViewModel {

    fun initInjection()

    fun getThemeLiveData(): LiveData<ThemeModel>

    fun clickBack()

}